package Controlador;

import DAO.DProducto;
import Modelo.MProducto;
import Vista.VGestionProductos;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

public class CGestionProductos {

    private final VGestionProductos vista;
    private final DProducto dao;

    public CGestionProductos(VGestionProductos vista) {
        this.vista = vista;
        this.dao = new DProducto();
    }

    public void iniciarControlador() {
        vista.getBtnAgregar().addActionListener(e -> agregarProducto());
        vista.getBtnModificar().addActionListener(e -> modificarProducto());
        vista.getBtnEliminar().addActionListener(e -> eliminarProducto());
        vista.getBtnListar().addActionListener(e -> listarProductos());
        vista.getTable().getSelectionModel().addListSelectionListener(e -> cargarProductoSeleccionado());

        cargarProveedores();
        listarProductos();
    }

    private void agregarProducto() {
        MProducto producto = vista.obtenerDatosProducto();

        if (!validarProducto(producto)) return;

        if (dao.agregarProducto(producto)) {
            JOptionPane.showMessageDialog(vista, "Producto agregado con éxito.");
            listarProductos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar el producto.");
        }
    }

    private void modificarProducto() {
        int codigoID = vista.obtenerIDProductoSeleccionado();
        if (codigoID == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto de la tabla para modificar.");
            return;
        }

        MProducto producto = vista.obtenerDatosProducto();
        producto.setCodigoID(codigoID);

        if (!validarProducto(producto)) return;

        if (dao.modificarProducto(producto)) {
            JOptionPane.showMessageDialog(vista, "Producto modificado con éxito.");
            listarProductos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al modificar el producto.");
        }
    }

    private void eliminarProducto() {
        int codigoID = vista.obtenerIDProductoSeleccionado();
        if (codigoID == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto de la tabla para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(vista,
                "¿Está seguro de que desea eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminarProducto(codigoID)) {
                JOptionPane.showMessageDialog(vista, "Producto eliminado con éxito.");
                listarProductos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar el producto.");
            }
        }
    }

    private void listarProductos() {
        List<MProducto> productos = dao.listarProductos();
        vista.mostrarProductos(productos);
    }

    private void cargarProductoSeleccionado() {
        int selectedRow = vista.getTable().getSelectedRow();
        if (selectedRow != -1) {
            int codigoID = Integer.parseInt(vista.getTable().getValueAt(selectedRow, 0).toString());
            String codigoBarras = (String) vista.getTable().getValueAt(selectedRow, 1);
            String nombreProducto = (String) vista.getTable().getValueAt(selectedRow, 2);
            int proveedorID = Integer.parseInt(vista.getTable().getValueAt(selectedRow, 3).toString());
            String stock = vista.getTable().getValueAt(selectedRow, 4).toString();
            String precioUnitario = vista.getTable().getValueAt(selectedRow, 5).toString();
            String costo = vista.getTable().getValueAt(selectedRow, 6).toString();
            String tipo = (String) vista.getTable().getValueAt(selectedRow, 7);

            vista.cargarDatosProducto(codigoID, codigoBarras, nombreProducto, proveedorID, stock, precioUnitario, costo, tipo);
        }
    }

    private void cargarProveedores() {
        List<String> proveedores = dao.obtenerProveedores();
        vista.cargarProveedores(proveedores);
    }

    private boolean validarProducto(MProducto producto) {
        if (producto.getNombreProducto().isEmpty() || producto.getPrecioUnitario().compareTo(BigDecimal.ZERO) <= 0 ||
            producto.getCosto().compareTo(BigDecimal.ZERO) <= 0 || producto.getStock().compareTo(BigDecimal.ZERO) < 0) {
            JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos correctamente.");
            return false;
        }

        if ("PZ".equals(producto.getTipo()) && (producto.getCodigoBarras() == null || producto.getCodigoBarras().isEmpty())) {
            JOptionPane.showMessageDialog(vista, "El código de barras es obligatorio para productos tipo 'PZ'.");
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        vista.limpiarCampos();
    }
}