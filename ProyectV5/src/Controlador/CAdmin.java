package Controlador;

import Vista.*;

import javax.swing.*;
import java.awt.*;

public class CAdmin {
    private final VAdmin vista;
    private final CCorteCaja corteCaja; // Instancia de CCorteCaja compartida


    public CAdmin(VAdmin vista, CCorteCaja corteCaja) {
        this.vista = vista;
        this.corteCaja = corteCaja; // Asignar la instancia compartida
    }

    public void configurarListeners(JButton btnCorteCaja, JButton btnGestionProductos,
                                     JButton btnConsultasVentas, JButton btnReportes,
                                     JButton btnAyuda, JButton btnSalir, JButton btnGestionProveedores, JButton btnDevoluciones) {
        btnCorteCaja.addActionListener(e -> abrirCorteCaja());
        btnGestionProductos.addActionListener(e -> abrirGestionProductos());
        btnConsultasVentas.addActionListener(e -> abrirConsultasVentas());
        btnReportes.addActionListener(e -> abrirReportes());
        btnAyuda.addActionListener(e -> abrirAyuda());
        btnSalir.addActionListener(e -> salir());
        btnGestionProveedores.addActionListener(e -> abrirGestionProveedores());
        btnDevoluciones.addActionListener(e -> abrirDevoluciones());
    }

    public void configurarMenu(JMenuBar menuBar) {
        JMenu mnMenu = new JMenu("Menu Principal");
        mnMenu.setFont(new Font("Arial", Font.BOLD, 14));

        JMenuItem miProductos = new JMenuItem("Productos");
        JMenuItem miAlmacen = new JMenuItem("Almac�n");
        JMenuItem miVenta = new JMenuItem("Venta");
        JMenuItem miReportes = new JMenuItem("Reportes");
        JMenuItem miProveedores = new JMenuItem("Gesti�n de Proveedores");
        JMenuItem miDevoluciones = new JMenuItem("Devoluciones");

        miProductos.addActionListener(e -> abrirGestionProductos());
        miVenta.addActionListener(e -> abrirCorteCaja());
        miReportes.addActionListener(e -> abrirReportes());
        miProveedores.addActionListener(e -> abrirGestionProveedores());
        miDevoluciones.addActionListener(e -> abrirDevoluciones());

        mnMenu.add(miProductos);
        mnMenu.add(miAlmacen);
        mnMenu.add(miVenta);
        mnMenu.add(miReportes);
        mnMenu.add(miProveedores);
        mnMenu.add(miDevoluciones);

        JMenu mnOpciones = new JMenu("Opciones");
        mnOpciones.setFont(new Font("Arial", Font.BOLD, 14));

        JMenuItem miSalir = new JMenuItem("Salir");
        miSalir.addActionListener(e -> salir());
        mnOpciones.add(miSalir);

        menuBar.add(mnMenu);
        menuBar.add(mnOpciones);
    }

    private void abrirCorteCaja() {
        VCorteCaja corteCajaView = new VCorteCaja(corteCaja);
        corteCajaView.setVisible(true);
    }


    private void abrirGestionProductos() {
        VGestionProductos gestionProductosView = new VGestionProductos();
        gestionProductosView.setVisible(true);

    }


    private void abrirConsultasVentas() {
        JOptionPane.showMessageDialog(vista, "Abrir Consultas de Ventas");
    }

    private void abrirReportes() {
        VReporteVentas reporte = new VReporteVentas();
        reporte.setVisible(true);
    }


    private void abrirAyuda() {
        VAyuda ayuda = new VAyuda();
        ayuda.setVisible(true);
    }

    private void abrirGestionProveedores() {
        VGestionProveedores gestionProveedores = new VGestionProveedores();
        gestionProveedores.setVisible(true);
    }

    private void abrirDevoluciones() {
    	VDevoluciones devoluciones = new VDevoluciones();
        devoluciones.setVisible(true);
    }

    private void salir() {
        // Mostrar cuadro de diálogo de confirmación
        int confirm = JOptionPane.showConfirmDialog(
            vista, 
            "¿Estás seguro de que quieres salir?", 
            "Confirmación", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Cerrar la ventana actual (VAdmin)
            vista.dispose();
            
            // Crear o mostrar la ventana principal (VPOSMenu)
            VPOSMenu vposMenu = new VPOSMenu(null); // Si necesitas pasar un controlador, cámbialo aquí
            vposMenu.setVisible(true);
        }
    }
    
}
