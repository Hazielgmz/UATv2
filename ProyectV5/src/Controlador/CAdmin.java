package Controlador;

import Vista.*;

import javax.swing.*;
import java.awt.*;

public class CAdmin {
    private final VAdmin vista;

    public CAdmin(VAdmin vista) {
        this.vista = vista;
    }

    public void configurarListeners(JButton btnCorteCaja, JButton btnGestionProductos, JButton btnInventarioGeneral,
                                     JButton btnConsultasVentas, JButton btnReportes, JButton btnConfiguracion, 
                                     JButton btnAyuda, JButton btnSalir, JButton btnGestionProveedores, JButton btnDevoluciones) {
        btnCorteCaja.addActionListener(e -> abrirCorteCaja());
        btnGestionProductos.addActionListener(e -> abrirGestionProductos());
        btnInventarioGeneral.addActionListener(e -> abrirInventarioGeneral());
        btnConsultasVentas.addActionListener(e -> abrirConsultasVentas());
        btnReportes.addActionListener(e -> abrirReportes());
        btnConfiguracion.addActionListener(e -> abrirConfiguracion());
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
        miAlmacen.addActionListener(e -> abrirInventarioGeneral());
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
        // Crear el controlador de Corte de Caja con valores iniciales (ejemplo)
        CCorteCaja controller = new CCorteCaja(300.0, 0.0, 0.0);

        VCorteCaja corteCajaView = new VCorteCaja(controller);
        corteCajaView.setVisible(true);
    }


    private void abrirGestionProductos() {
        VGestionProductos gestionProductosView = new VGestionProductos();
        gestionProductosView.setVisible(true);

    }

    private void abrirInventarioGeneral() {
    	VInventarioGeneral inventarioGeneral = new VInventarioGeneral(null);
    	inventarioGeneral.setVisible(true);
    	}

    private void abrirConsultasVentas() {
        JOptionPane.showMessageDialog(vista, "Abrir Consultas de Ventas");
    }

    private void abrirReportes() {
        JOptionPane.showMessageDialog(vista, "Abrir Reportes");
    }

    private void abrirConfiguracion() {
        JOptionPane.showMessageDialog(vista, "Abrir Configuracion");
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
        int confirm = JOptionPane.showConfirmDialog(vista, "�Est�s seguro de que quieres salir?", "Confirmaci�n", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            vista.dispose();
        }
    }
}
