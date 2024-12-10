package Controlador;

import DAO.DProveedor;
import Modelo.MProveedor;
import Vista.VGestionProveedores;

import javax.swing.*;
import java.util.List;

public class CGestionProveedores {

    private final VGestionProveedores vista;
    private final DProveedor dao;

    public CGestionProveedores(VGestionProveedores vista) {
        this.vista = vista;
        this.dao = new DProveedor();
    }

    public void iniciarControlador() {
        vista.getBtnAgregar().addActionListener(e -> agregarProveedor());
        vista.getBtnModificar().addActionListener(e -> modificarProveedor());
        vista.getBtnEliminar().addActionListener(e -> eliminarProveedor());
        vista.getBtnListar().addActionListener(e -> listarProveedores());
        vista.getTable().getSelectionModel().addListSelectionListener(e -> cargarProveedorSeleccionado());
    }

    private void agregarProveedor() {
        MProveedor proveedor = vista.obtenerDatosProveedor();
        if (proveedor.getNombre().isEmpty() || proveedor.getDireccion().isEmpty() ||
            proveedor.getTelefono().isEmpty() || proveedor.getEstado().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, completa todos los campos.");
            return;
        }

        if (dao.agregarProveedor(proveedor)) {
            JOptionPane.showMessageDialog(vista, "Proveedor agregado con éxito.");
            listarProveedores();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar proveedor.");
        }
    }

    private void modificarProveedor() {
        MProveedor proveedor = vista.obtenerDatosProveedor();
        if (proveedor.getProveedorID() <= 0) {
            JOptionPane.showMessageDialog(vista, "Selecciona un proveedor de la tabla para modificar.");
            return;
        }

        if (dao.modificarProveedor(proveedor)) {
            JOptionPane.showMessageDialog(vista, "Proveedor modificado con éxito.");
            listarProveedores();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al modificar proveedor.");
        }
    }

    private void eliminarProveedor() {
        int proveedorID = vista.obtenerIDProveedor();
        if (proveedorID <= 0) {
            JOptionPane.showMessageDialog(vista, "Selecciona un proveedor de la tabla para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(vista, "¿Estás seguro de que deseas eliminar este proveedor?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminarProveedor(proveedorID)) {
                JOptionPane.showMessageDialog(vista, "Proveedor eliminado con éxito.");
                listarProveedores();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar proveedor.");
            }
        }
    }

    private void listarProveedores() {
        List<MProveedor> proveedores = dao.listarProveedores();
        vista.mostrarProveedores(proveedores);
    }

    private void cargarProveedorSeleccionado() {
        int selectedRow = vista.getTable().getSelectedRow();
        if (selectedRow != -1) {
            int proveedorID = Integer.parseInt(vista.getTable().getValueAt(selectedRow, 0).toString());
            String nombre = vista.getTable().getValueAt(selectedRow, 1).toString();
            String direccion = vista.getTable().getValueAt(selectedRow, 2).toString();
            String telefono = vista.getTable().getValueAt(selectedRow, 3).toString();
            String estado = vista.getTable().getValueAt(selectedRow, 4).toString();

            vista.cargarDatosProveedor(proveedorID, nombre, direccion, telefono, estado);
        }
    }

    private void limpiarCampos() {
        vista.limpiarCampos();
    }
}