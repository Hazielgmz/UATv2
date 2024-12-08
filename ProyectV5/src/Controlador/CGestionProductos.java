package Controlador;

import Vista.VGestionProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CGestionProductos {

    private final VGestionProductos vista;

    public CGestionProductos(VGestionProductos vista) {
        this.vista = vista;
    }

    public void configurarListeners() {
        vista.getBtnAlta().addActionListener(e -> altaProducto());
        vista.getBtnBaja().addActionListener(e -> bajaProducto());
        vista.getBtnModificar().addActionListener(e -> modificarProducto());
        vista.getBtnActualizar().addActionListener(e -> actualizarTabla());
    }

    private void altaProducto() {
        // Simulación de alta de producto (deberías conectar esto con la lógica real)
        DefaultTableModel model = (DefaultTableModel) vista.getTableProductos().getModel();
        String id = JOptionPane.showInputDialog(vista, "Ingrese ID del producto:");
        String nombre = JOptionPane.showInputDialog(vista, "Ingrese nombre del producto:");
        String precio = JOptionPane.showInputDialog(vista, "Ingrese precio del producto:");
        String cantidad = JOptionPane.showInputDialog(vista, "Ingrese cantidad del producto:");

        if (id != null && nombre != null && precio != null && cantidad != null) {
            model.addRow(new Object[]{id, nombre, precio, cantidad});
        }
    }

    private void bajaProducto() {
        // Simulación de baja de producto
        int selectedRow = vista.getTableProductos().getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) vista.getTableProductos().getModel();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(vista, "Producto eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para eliminar.");
        }
    }

    private void modificarProducto() {
        // Simulación de modificación de producto
        int selectedRow = vista.getTableProductos().getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) vista.getTableProductos().getModel();
            String nombre = JOptionPane.showInputDialog(vista, "Ingrese nuevo nombre del producto:");
            String precio = JOptionPane.showInputDialog(vista, "Ingrese nuevo precio del producto:");
            String cantidad = JOptionPane.showInputDialog(vista, "Ingrese nueva cantidad del producto:");

            if (nombre != null && precio != null && cantidad != null) {
                model.setValueAt(nombre, selectedRow, 1);
                model.setValueAt(precio, selectedRow, 2);
                model.setValueAt(cantidad, selectedRow, 3);
                JOptionPane.showMessageDialog(vista, "Producto modificado correctamente.");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para modificar.");
        }
    }

    private void actualizarTabla() {
        // Simulación de actualización de la tabla
        JOptionPane.showMessageDialog(vista, "Tabla actualizada correctamente.");
    }
}
