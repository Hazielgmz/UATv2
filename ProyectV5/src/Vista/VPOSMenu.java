package Vista;
import javax.swing.*;

import Controlador.CPOSMenu;

import java.awt.*;
import java.util.HashMap;

public class VPOSMenu extends JFrame {
    private DefaultListModel<String> productListModel;
    private JList<String> productList;
    private JLabel orderTotalLabel;
    private CPOSMenu cposMenu;

    public VPOSMenu(DAO.DEmpleado userController) {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Crear instancia del controlador
        cposMenu = new CPOSMenu(this);

        // Configuración de pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Panel principal para el menú
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // Lista de frutas y precios
        String[] fruits = { "Manzana", "Aguacate", "Platano", "Fresa",
                            "Limon", "Sandia", "Zanahoria", "Durazno",
                            "Cebolla", "Tomate", "Lechuga", "Naranja",
                            "Granola", "Avena", "Cereal_Froot_Loops", "Cereal_Zucaritas",
                            "MAS PRODUCTOS" };
        double[] pricesPerKilo = { 30.0, 50.0, 20.0, 40.0 };

        // Mapa para asociar frutas con sus imágenes
        HashMap<String, String> fruitImages = new HashMap<>();
        for (String fruit : fruits) {
            fruitImages.put(fruit, "resources/" + fruit.toLowerCase().replace(" ", "_") + ".png");
        }

        // Crear botones con imágenes
        for (int i = 0; i < fruits.length; i++) {
            JButton button = new JButton(fruits[i]);

            // Cargar imagen desde el mapa
            String imagePath = fruitImages.get(fruits[i]);
            try {
                ImageIcon icon = new ImageIcon(imagePath);

                // Escalar la imagen para que se ajuste al botón
                Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                System.out.println("No se encontró la imagen para: " + fruits[i]);
            }

            // Configurar texto e imagen
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);

            // Asignar acción al botón
            if (i < 3) { // Los tres primeros productos usan un listener especial
                button.addActionListener(cposMenu.createFruitButtonListener(fruits[i], pricesPerKilo[i]));
            } else {
                button.addActionListener(e -> cposMenu.openFruitSearch());
            }

            menuPanel.add(button);
        }

        // Botón adicional para buscar por código de barras
        JButton barcodeSearchButton = new JButton("Buscar por código");
        barcodeSearchButton.addActionListener(e -> cposMenu.searchProductByBarcode());
        menuPanel.add(barcodeSearchButton);

        // Panel derecho para la lista de productos y botones de acciones
        JPanel rightPanel = new JPanel(new BorderLayout());

        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        JScrollPane scrollPane = new JScrollPane(productList);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JButton continueButton = new JButton("Continuar con la venta");
        continueButton.addActionListener(e -> cposMenu.continueSale());
        buttonPanel.add(continueButton);

        JButton deleteButton = new JButton("Eliminar Producto");
        deleteButton.addActionListener(e -> cposMenu.removeSelectedProduct());
        buttonPanel.add(deleteButton);

        JButton clearButton = new JButton("Vaciar Carrito");
        clearButton.addActionListener(e -> cposMenu.clearCart());
        buttonPanel.add(clearButton);

        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Panel inferior para botones adicionales
        JPanel actionPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));
        actionPanel.add(exitButton);

        JButton openButton = new JButton("Abrir");
        openButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Caja registradora abierta."));
        actionPanel.add(openButton);

        JButton moreButton = new JButton("mas...");
        moreButton.addActionListener(e -> cposMenu.abrirAdminView());
        actionPanel.add(moreButton);

        JButton lockButton = new JButton("Lock");
        lockButton.addActionListener(e -> cposMenu.lockPOS());
        actionPanel.add(lockButton);

        // Panel inferior para información adicional y botones
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new GridLayout(1, 3)); // Tres columnas para la información

        // Etiqueta para las transacciones
        JLabel transactionsLabel = new JLabel("Transacciones: 0", SwingConstants.CENTER);
        infoPanel.add(transactionsLabel);

        // Etiqueta para el usuario
        JLabel userLabel = new JLabel("Usuario: Haziel", SwingConstants.CENTER);
        infoPanel.add(userLabel);

        // Etiqueta para el total de la orden
        orderTotalLabel = new JLabel("Order Total: $0.00", SwingConstants.CENTER);
        infoPanel.add(orderTotalLabel);

        // Añadir el panel de información al panel inferior
        bottomPanel.add(infoPanel, BorderLayout.NORTH);

        // Añadir los botones (actionPanel) al centro del panel inferior
        bottomPanel.add(actionPanel, BorderLayout.CENTER);

        // Añadir el panel inferior al contenedor principal
        getContentPane().add(menuPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.EAST);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public DefaultListModel<String> getProductListModel() {
        return productListModel;
    }

    public JLabel getOrderTotalLabel() {
        return orderTotalLabel;
    }

    public JList<String> getProductList() {
        return productList;
    }
}
