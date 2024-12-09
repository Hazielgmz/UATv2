package Vista;

import Controlador.CPago;
import Controlador.CPOSMenu;

import javax.swing.*;
import java.awt.*;

public class VPago extends JFrame {
    private JTextArea carritoTextArea;
    private JLabel totalLabel, cambioLabel, ingresoLabel;
    private JTextField ingresoField;
    private CPago controlador;

    public VPago(double total, CPOSMenu cposMenu) {
        setTitle("Ventana de Pago - Vertical");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuraci�n de pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Inicializar el controlador
        controlador = new CPago(this, total, cposMenu);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Carrito de compras
        JPanel panelCarrito = new JPanel(new BorderLayout());
        carritoTextArea = new JTextArea(10, 30);
        carritoTextArea.setEditable(false);
        panelCarrito.add(new JScrollPane(carritoTextArea), BorderLayout.CENTER);

        totalLabel = new JLabel("Total a Pagar: $" + total);
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelCarrito.add(totalLabel, BorderLayout.SOUTH);

        mainPanel.add(panelCarrito);

        // Panel de m�todos de pago
        JPanel panelMetodosPago = controlador.crearPanelMetodosPago();
        mainPanel.add(panelMetodosPago);

        // Panel de ingreso y cambio
        JPanel panelIngresoCambio = controlador.crearPanelIngresoCambio();
        mainPanel.add(panelIngresoCambio);

        // Panel de billetes
        JPanel panelBilletes = controlador.crearPanelBilletes();
        mainPanel.add(panelBilletes);

        // Bot�n para completar compra
        JButton completarCompraButton = new JButton("Completar Compra");
        completarCompraButton.addActionListener(e -> controlador.completarCompra());
        mainPanel.add(completarCompraButton);

        // Bot�n para volver al men� principal
        JButton volverButton = new JButton("Volver al Men� Principal");
        volverButton.addActionListener(e -> controlador.volverAlMenu());
        mainPanel.add(volverButton);

        // Agregar panel principal a la ventana
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // M�todos para acceder y modificar los componentes desde el controlador
    public JTextArea getCarritoTextArea() {
        return carritoTextArea;
    }

    public JLabel getTotalLabel() {
        return totalLabel;
    }

    public JLabel getCambioLabel() {
        return cambioLabel;
    }

    public void setCambioLabel(JLabel cambioLabel) {
        this.cambioLabel = cambioLabel;
    }

    public JLabel getIngresoLabel() {
        return ingresoLabel;
    }

    public void setIngresoLabel(JLabel ingresoLabel) {
        this.ingresoLabel = ingresoLabel;
    }

    public JTextField getIngresoField() {
        return ingresoField;
    }

    public void setIngresoField(JTextField ingresoField) {
        this.ingresoField = ingresoField;
    }
}
