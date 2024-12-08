package Controlador;

import Vista.VPago;

import javax.swing.*;
import java.awt.*;

public class CPago {
    private final VPago vista;
    private final CPOSMenu cposMenu; // Controlador del menú principal
    private final double total;
    private String metodoPago = "";

    public CPago(VPago vista, double total, CPOSMenu cposMenu) {
        this.vista = vista;
        this.total = total;
        this.cposMenu = cposMenu;
    }

    public JPanel crearPanelMetodosPago() {
        JPanel panelMetodosPago = new JPanel(new GridLayout(3, 1, 5, 5));
        panelMetodosPago.setBorder(BorderFactory.createTitledBorder("Métodos de Pago"));

        JButton efectivoButton = new JButton("Pagar con Efectivo");
        efectivoButton.addActionListener(e -> {
            metodoPago = "Efectivo";
            calcularCambio();
        });

        JButton tarjetaButton = new JButton("Pagar con Visa/Mastercard");
        tarjetaButton.addActionListener(e -> {
            metodoPago = "Tarjeta";
            JOptionPane.showMessageDialog(vista, "Por favor, inserte su tarjeta Visa o Mastercard", "Pago con Tarjeta", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton amexButton = new JButton("Pagar con AMEX");
        amexButton.addActionListener(e -> {
            metodoPago = "AMEX";
            JOptionPane.showMessageDialog(vista, "Por favor, inserte su tarjeta AMEX", "Pago con AMEX", JOptionPane.INFORMATION_MESSAGE);
        });

        panelMetodosPago.add(efectivoButton);
        panelMetodosPago.add(tarjetaButton);
        panelMetodosPago.add(amexButton);

        return panelMetodosPago;
    }

    public JPanel crearPanelIngresoCambio() {
        JPanel panelIngresoCambio = new JPanel();
        panelIngresoCambio.setLayout(new BoxLayout(panelIngresoCambio, BoxLayout.Y_AXIS));
        panelIngresoCambio.setBorder(BorderFactory.createTitledBorder("Ingreso y Cambio"));

        vista.setIngresoLabel(new JLabel("Ingreso: $"));
        vista.setIngresoField(new JTextField(10));
        JPanel panelIngreso = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIngreso.add(vista.getIngresoLabel());
        panelIngreso.add(vista.getIngresoField());

        vista.setCambioLabel(new JLabel("Cambio: $0.0"));
        vista.getCambioLabel().setHorizontalAlignment(SwingConstants.CENTER);

        panelIngresoCambio.add(panelIngreso);
        panelIngresoCambio.add(vista.getCambioLabel());

        return panelIngresoCambio;
    }

    public JPanel crearPanelBilletes() {
        JPanel panelBilletes = new JPanel(new GridLayout(2, 3, 5, 5));
        panelBilletes.setBorder(BorderFactory.createTitledBorder("Billetes"));

        int[] billetes = {20, 50, 100, 200, 500, 1000};
        for (int billete : billetes) {
            JButton billeteButton = new JButton("$" + billete);
            billeteButton.addActionListener(e -> {
                double ingresoActual = Double.parseDouble(vista.getIngresoField().getText().isEmpty() ? "0" : vista.getIngresoField().getText());
                ingresoActual += billete;
                vista.getIngresoField().setText(String.valueOf(ingresoActual));
                calcularCambio();
            });
            panelBilletes.add(billeteButton);
        }

        return panelBilletes;
    }

    public void calcularCambio() {
        if (metodoPago.equals("Efectivo")) {
            try {
                double ingreso = Double.parseDouble(vista.getIngresoField().getText());
                double cambio = ingreso - total;
                vista.getCambioLabel().setText("Cambio: $" + (cambio >= 0 ? cambio : 0.0));
            } catch (NumberFormatException ex) {
                vista.getCambioLabel().setText("Cambio: $0.0");
            }
        } else {
            vista.getCambioLabel().setText("Cambio: $0.0");
        }
    }

    public void completarCompra() {
        if (metodoPago.equals("Efectivo")) {
            try {
                double ingreso = Double.parseDouble(vista.getIngresoField().getText());
                if (ingreso >= total) {
                    JOptionPane.showMessageDialog(vista, "Compra completada con éxito. Cambio: $" + (ingreso - total), "Compra Completada", JOptionPane.INFORMATION_MESSAGE);
                    cposMenu.clearCart();
                    vista.dispose();
                } else {
                    JOptionPane.showMessageDialog(vista, "El monto ingresado es insuficiente.", "Error de Pago", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (metodoPago.equals("Tarjeta") || metodoPago.equals("AMEX")) {
            JOptionPane.showMessageDialog(vista, "Compra completada con tarjeta. Gracias por su compra.", "Compra Completada", JOptionPane.INFORMATION_MESSAGE);
            cposMenu.clearCart();
            cposMenu.getView().setVisible(true); // Mostrar la ventana de POSMenu
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un método de pago.", "Método de Pago", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void volverAlMenu() {
        vista.dispose();
    }
    
}
