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

        cargarProveedores();
    }

    private void agregarProducto() {
        MProducto producto = vista.obtenerDatosProducto();
        
        if ("PZ".equals(producto.getTipo())) {
            if (producto.getCodigoBarras() == null || producto.getCodigoBarras().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El código de barras es obligatorio para productos tipo 'PZ'.");
                return;
            }
            if (dao.existeCodigoBarras(producto.getCodigoBarras(), 0)) {
                JOptionPane.showMessageDialog(null, "El código de barras ya existe.");
                return;
            }
        } else if ("GR".equals(producto.getTipo())) {
            producto.setCodigoBarras(null); // Aseguramos que el código de barras esté nulo para el tipo "GR"
        }
    
        if (dao.agregarProducto(producto)) {
            JOptionPane.showMessageDialog(null, "Producto agregado con éxito.");
            listarProductos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el producto.");
        }
    }
    
    private void modificarProducto() {
        if (vista.obtenerIDProductoSeleccionado() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para modificar.");
            return;
        }
    
        MProducto producto = vista.obtenerDatosProducto();
        
        if ("PZ".equals(producto.getTipo())) {
            if (producto.getCodigoBarras() == null || producto.getCodigoBarras().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El código de barras es obligatorio para productos tipo 'PZ'.");
                return;
            }
            if (dao.existeCodigoBarras(producto.getCodigoBarras(), producto.getCodigoID())) {
                JOptionPane.showMessageDialog(null, "El código de barras ya existe.");
                return;
            }
        } else if ("GR".equals(producto.getTipo())) {
            producto.setCodigoBarras(null); // Aseguramos que el código de barras esté nulo para el tipo "GR"
        }
    
        if (dao.modificarProducto(producto)) {
            JOptionPane.showMessageDialog(null, "Producto modificado con éxito.");
            listarProductos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar el producto.");
        }
    }
    
    private void eliminarProducto() {
        int codigoID = vista.obtenerIDProductoSeleccionado();
        if (codigoID == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar.");
            return;
        }
    
        if (dao.eliminarProducto(codigoID)) {
            JOptionPane.showMessageDialog(null, "Producto eliminado con éxito.");
            listarProductos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
        }
    }
    
    private void listarProductos() {
        List<MProducto> productos = dao.listarProductos();
        vista.mostrarProductos(productos);
    }

    private void cargarProveedores() {
        List<String> proveedores = dao.obtenerProveedores();
        vista.cargarProveedores(proveedores);
    }
}
