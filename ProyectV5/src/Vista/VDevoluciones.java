package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VDevoluciones extends JFrame {

    public VDevoluciones() {
        setTitle("Devoluciones - ID del Ticket");
        setSize(300, 160);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Etiqueta y campo de texto
        JLabel lblIDTicket = new JLabel("ID del Ticket:");
        lblIDTicket.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField txtIDTicket = new JTextField();
        txtIDTicket.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lblIDTicket, BorderLayout.NORTH);
        panel.add(txtIDTicket, BorderLayout.CENTER);

        // Botón
        JButton btnBuscar = new JButton("Buscar Ticket");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        btnBuscar.setBackground(new Color(100, 149, 237));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);

        btnBuscar.addActionListener(e -> {
            String idTicket = txtIDTicket.getText();
            if (!idTicket.isEmpty()) {
                mostrarProductosDelTicket(idTicket);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID de ticket válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(btnBuscar, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private void mostrarProductosDelTicket(String idTicket) {
        // Simulación de productos obtenidos (puedes reemplazar esto con datos reales de tu sistema)
        List<Producto> productos = obtenerProductosDelTicket(idTicket);

        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron productos para el ticket ID: " + idTicket, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear subventana
        JFrame ventanaProductos = new JFrame("Productos del Ticket: " + idTicket);
        ventanaProductos.setSize(400, 400);
        ventanaProductos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaProductos.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Selecciona los productos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Panel con checkboxes
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));

        List<JCheckBox> checkboxes = new ArrayList<>();
        for (Producto producto : productos) {
            JCheckBox checkBox = new JCheckBox(producto.getNombre() + " - $ " + producto.getPrecio());
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            checkboxes.add(checkBox);
            panelProductos.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(panelProductos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnContinuar = new JButton("Continuar");

        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(e -> ventanaProductos.dispose());

        btnContinuar.setFont(new Font("Arial", Font.BOLD, 14));
        btnContinuar.setBackground(new Color(34, 139, 34));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.addActionListener(e -> {
            List<String> seleccionados = new ArrayList<>();
            for (JCheckBox checkBox : checkboxes) {
                if (checkBox.isSelected()) {
                    seleccionados.add(checkBox.getText());
                }
            }

            if (seleccionados.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaProductos, "No seleccionaste ningún producto.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(ventanaProductos, "Productos seleccionados:\n" + String.join("\n", seleccionados), "Información", JOptionPane.INFORMATION_MESSAGE);
                ventanaProductos.dispose();
            }
        });

        panelBotones.add(btnCancelar);
        panelBotones.add(btnContinuar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        ventanaProductos.getContentPane().add(panel);
        ventanaProductos.setVisible(true);
    }

    private List<Producto> obtenerProductosDelTicket(String idTicket) {
        // Simulación de productos por ID de ticket
        // Reemplaza esta lógica con una consulta real a tu base de datos o sistema
        if ("123".equals(idTicket)) {
            return List.of(
                    new Producto("Producto A", 50.0),
                    new Producto("Producto B", 75.5),
                    new Producto("Producto C", 20.0)
            );
        } else if ("456".equals(idTicket)) {
            return List.of(
                    new Producto("Producto D", 99.9),
                    new Producto("Producto E", 150.0)
            );
        } else {
            return new ArrayList<>();
        }
    }
    // Clase interna para simular los productos
    static class Producto {
        private final String nombre;
        private final double precio;

        public Producto(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }
    }
}
