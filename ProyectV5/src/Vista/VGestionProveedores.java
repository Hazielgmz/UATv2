package Vista;

import Modelo.MProveedor;
import Controlador.CGestionProveedores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class VGestionProveedores extends JFrame {

    private JTextField txtID, txtNombre, txtDireccion, txtTelefono, txtEstado;
    private JButton btnAgregar, btnModificar, btnEliminar, btnListar;
    private JTable table;

    public VGestionProveedores() {
        setTitle("Gestión de Proveedores");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior para los campos de entrada
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridBagLayout());
        panelCampos.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        txtID = new JTextField(15);
        panelCampos.add(txtID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelCampos.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCampos.add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 1;
        txtDireccion = new JTextField(15);
        panelCampos.add(txtDireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCampos.add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 1;
        txtTelefono = new JTextField(15);
        panelCampos.add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelCampos.add(new JLabel("Estado:"), gbc);

        gbc.gridx = 1;
        txtEstado = new JTextField(15);
        panelCampos.add(txtEstado, gbc);

        add(panelCampos, BorderLayout.NORTH);

        // Panel central para la tabla
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        add(panelBotones, BorderLayout.SOUTH);

        CGestionProveedores controlador = new CGestionProveedores(this);
        controlador.iniciarControlador();
    }

    // Métodos para obtener datos de los campos
    public MProveedor obtenerDatosProveedor() {
        int id = txtID.getText().isEmpty() ? 0 : Integer.parseInt(txtID.getText());
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String estado = txtEstado.getText();

        return new MProveedor(id, nombre, direccion, telefono, estado);
    }

    public int obtenerIDProveedor() {
        return Integer.parseInt(txtID.getText());
    }

    public void mostrarProveedores(List<MProveedor> proveedores) {
        String[] columnas = {"ID", "Nombre", "Dirección", "Teléfono", "Estado"};
        String[][] datos = new String[proveedores.size()][5];

        for (int i = 0; i < proveedores.size(); i++) {
            MProveedor p = proveedores.get(i);
            datos[i][0] = String.valueOf(p.getProveedorID());
            datos[i][1] = p.getNombre();
            datos[i][2] = p.getDireccion();
            datos[i][3] = p.getTelefono();
            datos[i][4] = p.getEstado();
        }

        table.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    // Métodos para obtener botones
    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnListar() {
        return btnListar;
    }
}
