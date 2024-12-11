package Controlador;

import Vista.VPOSMenu;
import Vista.VPago;
import Modelo.MTicket;
import Reporte.RTicket;
import DAO.DTicket;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class CPago {
    private final VPago vista;
    private final double total;
    private final CPOSMenu cposMenu;
    private String metodoPago = "";
    private static double efectivoEnCaja = 500.0;
    private static double visaMasterEnCaja = 0.0;
    private static double amexEnCaja = 0.0;
    private final CCorteCaja corteCaja;


    public CPago(VPago vista, double total, CPOSMenu cposMenu, CCorteCaja corteCaja) {
        this.vista = vista;
        this.total = total;
        this.cposMenu = cposMenu;
        this.corteCaja = corteCaja;
    }

    // Método para cargar los productos en el carrito de la vista VPago
    public void cargarProductosEnCarrito(JTextArea carritoTextArea) {
        DefaultListModel<String> carritoProductos = cposMenu.getView().getProductListModel();
        carritoTextArea.setText("");
        for (int i = 0; i < carritoProductos.getSize(); i++) {
            carritoTextArea.append(carritoProductos.getElementAt(i) + "\n");
        }
    }

    // Crear panel de métodos de pago
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
            metodoPago = "Visa/Mastercard";
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

    // Crear panel de ingreso y cambio
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

        vista.getIngresoField().addActionListener(e -> calcularCambio());

        panelIngresoCambio.add(panelIngreso);
        panelIngresoCambio.add(vista.getCambioLabel());

        return panelIngresoCambio;
    }

    // Crear panel de billetes
    public JPanel crearPanelBilletes() {
        JPanel panelBilletes = new JPanel(new GridLayout(2, 3, 5, 5)); // 2 filas, 3 columnas con espacio de 5px
        panelBilletes.setBorder(BorderFactory.createTitledBorder("Billetes"));
    
        int[] billetes = {20, 50, 100, 200, 500, 1000};
        for (int billete : billetes) {
            JButton billeteButton = new JButton();
    
            // Configurar imagen (si existe) y establecer el ícono del botón
            String imagePath = "resources/" + billete + ".png";
            try {
                ImageIcon icon = new ImageIcon(imagePath);
                Image scaledImage = icon.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
                billeteButton.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                System.out.println("No se encontró la imagen para: " + billete);
            }
    
            billeteButton.setToolTipText("Billete de $" + billete); 
            billeteButton.addActionListener(e -> {
                double ingresoActual = Double.parseDouble(
                    vista.getIngresoField().getText().isEmpty() ? "0" : vista.getIngresoField().getText()
                );
                ingresoActual += billete;
                vista.getIngresoField().setText(String.valueOf(ingresoActual));
                calcularCambio();
            });
    
            panelBilletes.add(billeteButton);
        }
    
        return panelBilletes;
    }

    // Calcular el cambio
    public void calcularCambio() {
        if ("Efectivo".equals(metodoPago)) {
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
        if (metodoPago.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un método de pago.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        try {
            // Crear y configurar el objeto MTicket
            MTicket ticket = new MTicket();
            ticket.setEmpleadoID(1); // ID de ejemplo, reemplázalo por el ID real del empleado
            ticket.setProductoID(0); // Ajustar según los productos vendidos (puedes omitir si no se usa en tu lógica)
            ticket.setCantidad(BigDecimal.valueOf(1)); // Asignar cantidad como un valor fijo o dinámico
            ticket.setPrecioUnitario(BigDecimal.valueOf(total)); // Precio unitario
            ticket.setSubtotal(BigDecimal.valueOf(total)); // Subtotal
            ticket.setTotal(BigDecimal.valueOf(total)); // Total de la compra
            ticket.setFecha(LocalDateTime.now()); // Fecha y hora actual
            ticket.setDescuento(BigDecimal.ZERO); // Descuento por defecto
    
            // Insertar el ticket en la base de datos
            DTicket dao = new DTicket();
            boolean isInserted = dao.insertarTicket(ticket);
    
            if (isInserted) {
                // Actualizar el total de acuerdo al método de pago
            if ("Efectivo".equals(metodoPago)) {
                corteCaja.incrementarTotalEfectivo(total);
            } else if ("Visa/Mastercard".equals(metodoPago)) {
                corteCaja.incrementarTotalVisaMaster(total);
            } else if ("AMEX".equals(metodoPago)) {
                corteCaja.incrementarTotalAmex(total);
            }
                // Obtener productos del carrito
            DefaultListModel<String> carritoProductosModel = cposMenu.getView().getProductListModel();
            java.util.List<String> carritoProductos = new java.util.ArrayList<>();
            for (int i = 0; i < carritoProductosModel.getSize(); i++) {
                carritoProductos.add(carritoProductosModel.getElementAt(i));
            }
            // Generar el ticket en PDF
            RTicket.generarTicketPDF(metodoPago, total, carritoProductos);
                JOptionPane.showMessageDialog(vista, "Compra registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cposMenu.clearCart(); // Limpiar carrito
                vista.dispose(); // Cerrar la ventana de pago


                // Volver a la clase VPOSMenu
            VPOSMenu vposMenu = new VPOSMenu(null); // Pasa el controlador adecuado si es necesario
            vposMenu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar la compra. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Volver al menú principal
   public void volverAlMenu() {
    // Cerrar la ventana actual
    vista.dispose();

    // Crear y mostrar la ventana del menú principal
    VPOSMenu vposMenu = new VPOSMenu(null); // Asegúrate de pasar el controlador adecuado si es necesario
    vposMenu.setVisible(true);
}
 // Getter para obtener el total de efectivo en caja
 public static double getEfectivoEnCaja() {
    return efectivoEnCaja;
}
// Getter para obtener el total de efectivo en caja
public static double getVisaMasterEnCaja() {
    return visaMasterEnCaja;
}// Getter para obtener el total de efectivo en caja
public static double getAmexEnCaja() {
    return amexEnCaja;
}
}
