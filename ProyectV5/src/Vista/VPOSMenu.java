package Vista;

import Controlador.CPOSMenu;
import Modelo.MProducto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

        // Obtener productos desde la base de datos (tipo "GR")
        List<MProducto> productos = cposMenu.getProductosPorTipo("GR");

        // Crear botones dinámicamente desde los productos (máximo 16)
        int maxButtons = 16; // Límite de botones
        int buttonCount = 0; // Contador de botones creados

        for (MProducto producto : productos) {
            if (buttonCount >= maxButtons) {
        break; // Salir del bucle si ya se alcanzó el límite
    }

        JButton button = new JButton(producto.getNombreProducto());

     // Configurar imagen (si existe)
        String imagePath = "resources/" + producto.getNombreProducto().toLowerCase().replace(" ", "_") + ".png";
        try {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
        System.out.println("No se encontró la imagen para: " + producto.getNombreProducto());
    }

    // Configurar texto e imagen
    button.setHorizontalTextPosition(SwingConstants.CENTER);
    button.setVerticalTextPosition(SwingConstants.BOTTOM);

    // Asignar acción al botón
    button.addActionListener(cposMenu.createDynamicButtonListener(producto));

    menuPanel.add(button);

    buttonCount++; // Incrementar el contador de botones creados
}
        // Botón "MAS PRODUCTOS"
        JButton moreProductsButton = new JButton("MAS PRODUCTOS");
        moreProductsButton.addActionListener(e -> cposMenu.openMoreProductsDialog());
        menuPanel.add(moreProductsButton);

        // Botón "BUSCAR"
        JButton searchButton = new JButton("BUSCAR");
        searchButton.addActionListener(e -> cposMenu.searchProductByBarcode());
        menuPanel.add(searchButton);

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

        JButton moreButton = new JButton("Más...");
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

        // Añadir los paneles al contenedor principal
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
