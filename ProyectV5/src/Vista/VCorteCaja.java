package Vista;

import Controlador.CCorteCaja;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class VCorteCaja extends JFrame {
    private JLabel efectivoLabel;
    private JLabel visaMasterLabel;
    private JLabel amexLabel;
    private JTextField retiroTextField;
    private JComboBox<String> metodoRetiroComboBox;
    private JButton retirarButton;
    private CCorteCaja controller;

    public VCorteCaja(CCorteCaja controller) {
        this.controller = controller;
        setTitle("Corte de Caja");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        mainPanel.add(crearPanelTotales(), BorderLayout.NORTH);
        mainPanel.add(crearPanelRetiro(), BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        add(new JLabel("  "), BorderLayout.WEST);
        add(new JLabel("  "), BorderLayout.EAST);
    }

    private JPanel crearPanelTotales() {
        JPanel panelTotales = new JPanel(new GridLayout(3, 1, 10, 10));
        panelTotales.setBorder(new TitledBorder("Totales del Día"));
        
        efectivoLabel = new JLabel("Efectivo: $" + controller.getTotalEfectivo());
        visaMasterLabel = new JLabel("Visa/Master: $" + controller.getTotalVisaMaster());
        amexLabel = new JLabel("AMEX: $" + controller.getTotalAmex());

        efectivoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        visaMasterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        amexLabel.setFont(new Font("Arial", Font.BOLD, 14));

        panelTotales.add(efectivoLabel);
        panelTotales.add(visaMasterLabel);
        panelTotales.add(amexLabel);

        return panelTotales;
    }

    private JPanel crearPanelRetiro() {
        JPanel panelRetiro = new JPanel(new GridLayout(3, 2, 10, 10));
        panelRetiro.setBorder(new TitledBorder("Retiro de Fondos"));

        retiroTextField = new JTextField();
        metodoRetiroComboBox = new JComboBox<>(new String[]{"Efectivo", "Visa/Master", "AMEX"});
        retirarButton = new JButton("Retirar");
        retirarButton.addActionListener(e -> realizarRetiro());

        panelRetiro.add(new JLabel("Monto a Retirar:"));
        panelRetiro.add(retiroTextField);

        panelRetiro.add(new JLabel("Método de Retiro:"));
        panelRetiro.add(metodoRetiroComboBox);

        panelRetiro.add(new JLabel());
        panelRetiro.add(retirarButton);

        return panelRetiro;
    }

    private void realizarRetiro() {
        try {
            double monto = Double.parseDouble(retiroTextField.getText());
            String metodo = (String) metodoRetiroComboBox.getSelectedItem();
            if (controller.procesarRetiro(monto, metodo)) {
                actualizarTotales();
                JOptionPane.showMessageDialog(this, "Retiro de $" + monto + " en " + metodo + " realizado.");
            } else {
                JOptionPane.showMessageDialog(this, "Fondos insuficientes en " + metodo + ".");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un monto válido.");
        }
    }

    private void actualizarTotales() {
        efectivoLabel.setText("Efectivo: $" + controller.getTotalEfectivo());
        visaMasterLabel.setText("Visa/Master: $" + controller.getTotalVisaMaster());
        amexLabel.setText("AMEX: $" + controller.getTotalAmex());
    }
}
