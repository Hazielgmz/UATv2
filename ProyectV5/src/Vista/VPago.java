package Vista;
import Controlador.CPago;
import Controlador.CCorteCaja;
import Controlador.CPOSMenu;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class VPago extends JFrame {
    private JTextArea carritoTextArea;
    private JLabel totalLabel, cambioLabel, ingresoLabel;
    private JTextField ingresoField;
    private CPago controlador;

    public VPago(double total, CPOSMenu cposMenu, CCorteCaja corteCaja) {
        setTitle("Ventana de Pago - Vertical");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuración de pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Inicializar el controlador
        controlador = new CPago(this, total, cposMenu, corteCaja);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Carrito de compras
        JPanel panelCarrito = new JPanel(new BorderLayout());
        carritoTextArea = new JTextArea(10, 30);
        carritoTextArea.setEditable(false);
        carritoTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        carritoTextArea.setBackground(new Color(240, 248, 255));
        panelCarrito.setBorder(BorderFactory.createTitledBorder("Carrito de Compras"));
        panelCarrito.add(new JScrollPane(carritoTextArea), BorderLayout.CENTER);

        totalLabel = new JLabel("Total a Pagar: $" + total);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(Color.BLUE);
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelCarrito.add(totalLabel, BorderLayout.SOUTH);

        mainPanel.add(panelCarrito);

        // Panel de métodos de pago
        JPanel panelMetodosPago = controlador.crearPanelMetodosPago();
        panelMetodosPago.setBorder(BorderFactory.createTitledBorder("Métodos de Pago"));
        mainPanel.add(panelMetodosPago);

        // Panel de ingreso y cambio
        JPanel panelIngresoCambio = controlador.crearPanelIngresoCambio();
        panelIngresoCambio.setBorder(BorderFactory.createTitledBorder("Ingreso y Cambio"));
        mainPanel.add(panelIngresoCambio);

        // Panel de billetes
        JPanel panelBilletes = controlador.crearPanelBilletes();
        panelBilletes.setBorder(BorderFactory.createTitledBorder("Billetes"));
        mainPanel.add(panelBilletes);

        // Botón para completar compra
        JButton completarCompraButton = new JButton("Completar Compra");
        completarCompraButton.setFont(new Font("Arial", Font.BOLD, 16));
        completarCompraButton.setBackground(new Color(34, 139, 34));
        completarCompraButton.setForeground(Color.WHITE);
        completarCompraButton.addActionListener(e -> controlador.completarCompra());
        mainPanel.add(completarCompraButton);

        // Botón para volver al menú principal
        JButton volverButton = new JButton("Volver al Menú Principal");
        volverButton.setFont(new Font("Arial", Font.BOLD, 16));
        volverButton.setBackground(new Color(220, 20, 60));
        volverButton.setForeground(Color.WHITE);
        volverButton.addActionListener(e -> controlador.volverAlMenu());
        mainPanel.add(volverButton);

        // Agregar panel principal a la ventana
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void limpiarInterfaz() {
        carritoTextArea.setText("");
        ingresoField.setText("");
        cambioLabel.setText("Cambio: $0.0");
        totalLabel.setText("Total a Pagar: $0.0");
    }

    public void actualizarCarrito(String textoCarrito) {
        carritoTextArea.setText(textoCarrito);
    }

    // Métodos para acceder y modificar los componentes desde el controlador
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