package Vista;

import Modelo.MProveedor;
import Controlador.CGestionProveedores;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class VGestionProveedores extends JFrame {

    private JTextField txtID, txtNombre, txtDireccion, txtTelefono, txtEstado;
    private JButton btnAgregar, btnModificar, btnEliminar, btnListar;
    private JTable table;

    public VGestionProveedores() {
        setTitle("Gestión de Proveedores");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255)); // Azul claro

        // Panel superior para los campos de entrada
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridBagLayout());
        panelCampos.setBackground(Color.WHITE); // Fondo blanco
        panelCampos.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                        "Datos del Proveedor",
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        new Color(50, 50, 50)
                ),
                new EmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos de texto y etiquetas
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

        add(panelCampos, BorderLayout.WEST);

        // Panel central para la tabla
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                        "Lista de Proveedores",
                        TitledBorder.CENTER,
                        TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 14),
                        new Color(50, 50, 50)
                ),
                new EmptyBorder(10, 10, 10, 10)
        ));
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(240, 248, 255));

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        // Estilo de los botones
        for (JButton button : new JButton[]{btnAgregar, btnModificar, btnEliminar, btnListar}) {
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setBackground(new Color(100, 149, 237)); // Azul
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
            panelBotones.add(button);
        }

        add(panelBotones, BorderLayout.SOUTH);

        // Vincular controlador
        CGestionProveedores controlador = new CGestionProveedores(this);
        controlador.iniciarControlador();
    }

    // Métodos para obtener datos de los campos
    public MProveedor obtenerDatosProveedor() {
        try {
            int id = txtID.getText().isEmpty() ? 0 : Integer.parseInt(txtID.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String estado = txtEstado.getText();

            return new MProveedor(id, nombre, direccion, telefono, estado);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public int obtenerIDProveedor() {
        try {
            return Integer.parseInt(txtID.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public void mostrarProveedores(List<MProveedor> proveedores) {
        if (proveedores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron proveedores.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

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

    public JTable getTable() {
        return table;
    }

    public void cargarDatosProveedor(int id, String nombre, String direccion, String telefono, String estado) {
        txtID.setText(String.valueOf(id));
        txtNombre.setText(nombre);
        txtDireccion.setText(direccion);
        txtTelefono.setText(telefono);
        txtEstado.setText(estado);
    }

    public void limpiarCampos() {
        txtID.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEstado.setText("");
    }
}