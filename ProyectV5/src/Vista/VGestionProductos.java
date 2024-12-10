package Vista;

import Modelo.MProducto;
import Controlador.CGestionProductos;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class VGestionProductos extends JFrame {

    private JTextField txtCodigoID, txtCodigoBarras, txtNombreProducto, txtStock, txtPrecioUnitario, txtCosto;
    private JComboBox<String> cbProveedor, cbTipo;
    private JButton btnAgregar, btnModificar, btnEliminar, btnListar;
    private JTable table;
    private int selectedCodigoID = -1; // Almacena el ID seleccionado

    public VGestionProductos() {
        setTitle("Gestión de Productos");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)),
                "Datos del Producto",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(50, 50, 50)
        ));
        panelCampos.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo: Código ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Código ID:"), gbc);
        gbc.gridx = 1;
        txtCodigoID = new JTextField(15);
        txtCodigoID.setEditable(true);
        panelCampos.add(txtCodigoID, gbc);

        // Etiqueta y campo: Código de Barras
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(new JLabel("Código de Barras:"), gbc);
        gbc.gridx = 1;
        txtCodigoBarras = new JTextField(15);
        panelCampos.add(txtCodigoBarras, gbc);

        // Etiqueta y campo: Nombre del Producto
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCampos.add(new JLabel("Nombre del Producto:"), gbc);
        gbc.gridx = 1;
        txtNombreProducto = new JTextField(15);
        panelCampos.add(txtNombreProducto, gbc);

        // Etiqueta y campo: Proveedor
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCampos.add(new JLabel("Proveedor:"), gbc);
        gbc.gridx = 1;
        cbProveedor = new JComboBox<>();
        panelCampos.add(cbProveedor, gbc);

        // Etiqueta y campo: Stock
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelCampos.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1;
        txtStock = new JTextField(15);
        panelCampos.add(txtStock, gbc);

        // Etiqueta y campo: Precio Unitario
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelCampos.add(new JLabel("Precio Unitario:"), gbc);
        gbc.gridx = 1;
        txtPrecioUnitario = new JTextField(15);
        panelCampos.add(txtPrecioUnitario, gbc);

        // Etiqueta y campo: Costo
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelCampos.add(new JLabel("Costo:"), gbc);
        gbc.gridx = 1;
        txtCosto = new JTextField(15);
        panelCampos.add(txtCosto, gbc);

        // Etiqueta y campo: Tipo (PZ o GR)
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelCampos.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        cbTipo = new JComboBox<>(new String[]{"PZ", "GR"});
        panelCampos.add(cbTipo, gbc);

        add(panelCampos, BorderLayout.WEST);

        // Tabla central para mostrar productos
        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)),
                "Lista de Productos",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                new Color(50, 50, 50)
        ));
        add(tableScrollPane, BorderLayout.CENTER);

        // Agregar listener para seleccionar la fila
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    selectedCodigoID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                    txtCodigoID.setText(table.getValueAt(selectedRow, 0).toString());
                    txtCodigoBarras.setText(table.getValueAt(selectedRow, 1).toString());
                    txtNombreProducto.setText(table.getValueAt(selectedRow, 2).toString());
                    cbProveedor.setSelectedItem(table.getValueAt(selectedRow, 3).toString());
                    txtStock.setText(table.getValueAt(selectedRow, 4).toString());
                    txtPrecioUnitario.setText(table.getValueAt(selectedRow, 5).toString());
                    txtCosto.setText(table.getValueAt(selectedRow, 6).toString());
                    cbTipo.setSelectedItem(table.getValueAt(selectedRow, 7).toString());
                }
            }
        });

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(240, 240, 240));

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        // Estilizar botones
        for (JButton btn : new JButton[]{btnAgregar, btnModificar, btnEliminar, btnListar}) {
            btn.setPreferredSize(new Dimension(120, 40));
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setBackground(new Color(50, 150, 250));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(new Color(30, 100, 200)));
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.SOUTH);

        // Vincular controlador
        CGestionProductos controlador = new CGestionProductos(this);
        controlador.iniciarControlador();
    }

    // Métodos para obtener el ID seleccionado
    public int obtenerIDProductoSeleccionado() {
        return selectedCodigoID;
    }

    // Métodos para obtener datos ingresados
    public MProducto obtenerDatosProducto() {
        int codigoID = txtCodigoID.getText().isEmpty() ? 0 : Integer.parseInt(txtCodigoID.getText());
        String codigoBarras = txtCodigoBarras.getText();
        String nombreProducto = txtNombreProducto.getText();
        int proveedorID = Integer.parseInt(cbProveedor.getSelectedItem().toString().split(" - ")[0]);
        BigDecimal stock = new BigDecimal(txtStock.getText());
        BigDecimal precioUnitario = new BigDecimal(txtPrecioUnitario.getText());
        BigDecimal costo = new BigDecimal(txtCosto.getText());
        String tipo = cbTipo.getSelectedItem().toString();

        return new MProducto(codigoID, codigoBarras, nombreProducto, proveedorID, stock, precioUnitario, costo, tipo);
    }

    public void mostrarProductos(List<MProducto> productos) {
        String[] columnas = {"ID", "Código de Barras", "Nombre", "Proveedor", "Stock", "Precio Unitario", "Costo", "Tipo"};
        String[][] datos = new String[productos.size()][8];

        for (int i = 0; i < productos.size(); i++) {
            MProducto p = productos.get(i);
            datos[i][0] = String.valueOf(p.getCodigoID());
            datos[i][1] = p.getCodigoBarras();
            datos[i][2] = p.getNombreProducto();
            datos[i][3] = String.valueOf(p.getProveedorID());
            datos[i][4] = p.getStock().toString();
            datos[i][5] = p.getPrecioUnitario().toString();
            datos[i][6] = p.getCosto().toString();
            datos[i][7] = p.getTipo();
        }

        table.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    public void cargarProveedores(List<String> proveedores) {
        cbProveedor.removeAllItems();
        for (String proveedor : proveedores) {
            cbProveedor.addItem(proveedor);
        }
    }

    // Métodos para acceder a los botones
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

    public void limpiarCampos() {
        txtCodigoID.setText("");
        txtCodigoBarras.setText("");
        txtNombreProducto.setText("");
        cbProveedor.setSelectedIndex(0);
        txtStock.setText("");
        txtPrecioUnitario.setText("");
        txtCosto.setText("");
        cbTipo.setSelectedIndex(0);
    }
    public void cargarDatosProducto(int codigoID, String codigoBarras, String nombreProducto, int proveedorID,
                                String stock, String precioUnitario, String costo, String tipo) {
    txtCodigoID.setText(String.valueOf(codigoID));
    txtCodigoBarras.setText(codigoBarras);
    txtNombreProducto.setText(nombreProducto);
    cbProveedor.setSelectedItem(String.valueOf(proveedorID));
    txtStock.setText(stock);
    txtPrecioUnitario.setText(precioUnitario);
    txtCosto.setText(costo);
    cbTipo.setSelectedItem(tipo);
}

}