package Controlador;

import Vista.VAdmin;
import Vista.VPOSMenu;
import Vista.VPago;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CPOSMenu {
    private final VPOSMenu view;
    private double orderTotal;

    public CPOSMenu(VPOSMenu view) {
        this.view = view;
        this.orderTotal = 0.00;
    }

    public ActionListener createFruitButtonListener(String fruit, double pricePerKilo) {
        return e -> {
            String input = JOptionPane.showInputDialog(view, "Ingrese el peso en kg de " + fruit + ":");
            try {
                double weight = Double.parseDouble(input);
                double totalPrice = weight * pricePerKilo;
                view.getProductListModel().addElement(fruit + " (" + weight + " kg) - $" + String.format("%.2f", totalPrice));
                orderTotal += totalPrice;
                view.getOrderTotalLabel().setText("Order Total: $" + String.format("%.2f", orderTotal));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Por favor, ingrese un valor num�rico v�lido para el peso.");
            }
        };
    }

    public void openFruitSearch() {
        String[] moreFruits = { "Limon", "Lechuga", "Fresas", "Naranjas" };
        double[] moreFruitPrices = { 15.0, 10.0, 60.0, 25.0 };

        String selectedFruit = (String) JOptionPane.showInputDialog(view, "Seleccione una fruta:", "Buscar Frutas",
                JOptionPane.PLAIN_MESSAGE, null, moreFruits, moreFruits[0]);

        if (selectedFruit != null) {
            for (int i = 0; i < moreFruits.length; i++) {
                if (selectedFruit.equals(moreFruits[i])) {
                    createFruitButtonListener(moreFruits[i], moreFruitPrices[i]).actionPerformed(null);
                    break;
                }
            }
        }
    }

    public void searchProductByBarcode() {
        String barcode = JOptionPane.showInputDialog(view, "Ingrese el n�mero de c�digo de barras:");
        if (barcode != null && !barcode.isEmpty()) {
            String productName = "Producto " + barcode;
            double productPrice = 10.0;
            view.getProductListModel().addElement(productName + " - $" + String.format("%.2f", productPrice));
            orderTotal += productPrice;
            view.getOrderTotalLabel().setText("Order Total: $" + String.format("%.2f", orderTotal));
        } else {
            JOptionPane.showMessageDialog(view, "Por favor, ingrese un c�digo de barras v�lido.");
        }
    }

    public void continueSale() {
        if (view.getProductListModel().isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay productos en la venta.");
            return;
        }

        // Obtener el total de la orden
        double orderTotal = Double.parseDouble(view.getOrderTotalLabel().getText().replace("Order Total: $", ""));

        // Crear una instancia de VPago y pasar CPOSMenu como controlador
        VPago vPago = new VPago(orderTotal, this); // Pasamos el total y el controlador actual
        vPago.setVisible(true); // Hacemos visible la ventana de pago

        view.setVisible(false); // Oculta la ventana actual del men� principal
    }

    public void removeSelectedProduct() {
        int selectedIndex = view.getProductList().getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedProduct = view.getProductListModel().getElementAt(selectedIndex);
            String[] productDetails = selectedProduct.split(" - \\$");
            double price = Double.parseDouble(productDetails[1]);
            orderTotal -= price;
            view.getOrderTotalLabel().setText("Order Total: $" + String.format("%.2f", orderTotal));
            view.getProductListModel().remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona un producto para eliminar.");
        }
    }

    public void clearCart() {
        view.getProductListModel().clear();
        orderTotal = 0.00;
        view.getOrderTotalLabel().setText("Order Total: $0.00");
        JOptionPane.showMessageDialog(view, "El carrito ha sido vaciado.");
    }

    public void abrirAdminView() {
    	 // Solicitar la contrase�a al usuario
        String password = JOptionPane.showInputDialog(view, "Ingrese la contrase�a para acceder a la administraci�n:");

        // Verificar la contrase�a
        if ("3312".equals(password)) {
            // Contrase�a correcta: abrir la ventana de administraci�n
            VAdmin adminView = new VAdmin();
            adminView.setVisible(true); // Mostrar la ventana de administraci�n
            view.setVisible(false); // Oculta la ventana actual del men�
            
        } else {
            // Contrase�a incorrecta: mostrar mensaje de error
            JOptionPane.showMessageDialog(view, "Contrase�a incorrecta. Int�ntelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void lockPOS() {
        String password = JOptionPane.showInputDialog(view, "Ingrese la contrase�a para desbloquear:");
        if ("3312".equals(password)) {
            JOptionPane.showMessageDialog(view, "Punto de venta desbloqueado.");
        } else {
            JOptionPane.showMessageDialog(view, "Contrase�a incorrecta. Int�ntelo nuevamente.");
            lockPOS();
        }
    }
    public VPOSMenu getView() {
        return view;
    }
}
