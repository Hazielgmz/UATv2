package Vista;

import Controlador.CGestionProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VGestionProductos extends JFrame {

    private JTable tableProductos;
    private JButton btnAlta, btnBaja, btnModificar, btnActualizar;
    private CGestionProductos controlador; // Controlador para manejar la l�gica

    public VGestionProductos() {
        setTitle("Gesti�n de Productos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cambiar el fondo y el estilo de la ventana
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(new BorderLayout(10, 10));

        // Configuraci�n de la tabla con un dise�o elegante
        String[] columnNames = {"ID", "Nombre", "Precio", "Cantidad"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        tableProductos = new JTable(model);
        tableProductos.setFillsViewportHeight(true);
        tableProductos.setRowHeight(25);
        tableProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableProductos.getTableHeader().setBackground(new Color(173, 216, 230));
        tableProductos.getTableHeader().setForeground(Color.BLACK);
        tableProductos.setFont(new Font("Arial", Font.PLAIN, 14));
        tableProductos.setGridColor(new Color(224, 224, 224));

        JScrollPane scrollPane = new JScrollPane(tableProductos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones de gesti�n con dise�o elegante
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(240, 248, 255)); // Fondo similar al resto de la ventana

        btnAlta = new JButton("Alta");
        btnBaja = new JButton("Baja");
        btnModificar = new JButton("Modificar");
        btnActualizar = new JButton("Actualizar");

        // Estilos de botones para un aspecto m�s elegante
        JButton[] botones = {btnAlta, btnBaja, btnModificar, btnActualizar};
        for (JButton boton : botones) {
            boton.setBackground(new Color(100, 149, 237));
            boton.setForeground(Color.WHITE);
            boton.setFont(new Font("Arial", Font.BOLD, 12));
            boton.setFocusPainted(false);
            boton.setPreferredSize(new Dimension(100, 30));
        }

        panelBotones.add(btnAlta);
        panelBotones.add(btnBaja);
        panelBotones.add(btnModificar);
        panelBotones.add(btnActualizar);

        add(panelBotones, BorderLayout.SOUTH);

        // Asignar controlador
        controlador = new CGestionProductos(this);
        controlador.configurarListeners();
    }

    public JTable getTableProductos() {
        return tableProductos;
    }

    public JButton getBtnAlta() {
        return btnAlta;
    }

    public JButton getBtnBaja() {
        return btnBaja;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }
}
