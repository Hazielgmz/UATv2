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
    }

    private void agregarProveedor() {
        MProveedor proveedor = vista.obtenerDatosProveedor();
        if (dao.agregarProveedor(proveedor)) {
            JOptionPane.showMessageDialog(null, "Proveedor agregado con éxito.");
            listarProveedores();
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar proveedor.");
        }
    }

    private void modificarProveedor() {
        MProveedor proveedor = vista.obtenerDatosProveedor();
        if (dao.modificarProveedor(proveedor)) {
            JOptionPane.showMessageDialog(null, "Proveedor modificado con éxito.");
            listarProveedores();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar proveedor.");
        }
    }

    private void eliminarProveedor() {
        int proveedorID = vista.obtenerIDProveedor();
        if (dao.eliminarProveedor(proveedorID)) {
            JOptionPane.showMessageDialog(null, "Proveedor eliminado con éxito.");
            listarProveedores();
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar proveedor.");
        }
    }

    private void listarProveedores() {
        List<MProveedor> proveedores = dao.listarProveedores();
        vista.mostrarProveedores(proveedores);
    }
}
