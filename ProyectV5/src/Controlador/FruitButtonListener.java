package Controlador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FruitButtonListener implements ActionListener {
    private String fruitName;
    private double pricePerKilo;
    private DefaultListModel<String> productListModel;
    private JLabel orderTotalLabel;
    private double orderTotal;

    public FruitButtonListener(String fruitName, double pricePerKilo, DefaultListModel<String> productListModel, JLabel orderTotalLabel) {
        this.fruitName = fruitName;
        this.pricePerKilo = pricePerKilo;
        this.productListModel = productListModel;
        this.orderTotalLabel = orderTotalLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(null, "Ingrese el peso en kg de " + fruitName + ":");
        try {
            double weight = Double.parseDouble(input);
            double totalPrice = weight * pricePerKilo;
            productListModel.addElement(fruitName + " (" + weight + " kg) - $" + String.format("%.2f", totalPrice));
            orderTotal += totalPrice;
            orderTotalLabel.setText("Order Total: $" + String.format("%.2f", orderTotal));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor numérico válido para el peso.");
        }
    }
}

