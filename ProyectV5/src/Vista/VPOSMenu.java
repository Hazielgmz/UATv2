package Vista;

import Controlador.CPOSMenu;
import javax.swing.*;
import java.awt.*;

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

        // Configuraci�n de pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Panel principal para el men�
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] fruits = { "Manzana", "Aguacate", "Platano", "mas..." };
        double[] pricesPerKilo = { 30.0, 50.0, 20.0, 0.0 };

        for (int i = 0; i < fruits.length; i++) {
            JButton button = new JButton(fruits[i]);
            if (i < 3) {
                button.addActionListener(cposMenu.createFruitButtonListener(fruits[i], pricesPerKilo[i]));
            } else {
                button.addActionListener(e -> cposMenu.openFruitSearch());
            }
            menuPanel.add(button);
        }

        JButton barcodeSearchButton = new JButton("Buscar por cdigo");
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

        // Panel inferior para informaci�n adicional y botones
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new GridLayout(1, 3)); // Tres columnas para la informaci�n

        // Etiqueta para las transacciones
        JLabel transactionsLabel = new JLabel("Transacciones: 0", SwingConstants.CENTER);
        infoPanel.add(transactionsLabel);

        // Etiqueta para el usuario
        JLabel userLabel = new JLabel("Usuario: Haziel", SwingConstants.CENTER);
        infoPanel.add(userLabel);

        // Etiqueta para el total de la orden
        orderTotalLabel = new JLabel("Order Total: $0.00", SwingConstants.CENTER);
        infoPanel.add(orderTotalLabel);

        // A�adir el panel de informaci�n al panel inferior
        bottomPanel.add(infoPanel, BorderLayout.NORTH);

        // A�adir los botones (actionPanel) al centro del panel inferior
        bottomPanel.add(actionPanel, BorderLayout.CENTER);

        // A�adir el panel inferior al contenedor principal
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
