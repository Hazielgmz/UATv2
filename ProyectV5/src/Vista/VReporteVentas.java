package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controlador.CReporteVentas;

public class VReporteVentas extends JFrame {

    private JButton btnReporteUsuarios;
    private JButton btnReporteProductos;
    private CReporteVentas controlador;

    public VReporteVentas() {
        setTitle("Reportes de Ventas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        controlador = new CReporteVentas();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Panel principal
        JPanel panelPrincipal = new JPanel(new GridLayout(2, 1, 10, 20));
        getContentPane().add(panelPrincipal);

        // Botón para generar reporte de usuarios
        btnReporteUsuarios = new JButton("Generar Reporte de Usuarios");
        btnReporteUsuarios.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(btnReporteUsuarios);

        // Botón para generar reporte de productos
        btnReporteProductos = new JButton("Generar Reporte de Productos");
        btnReporteProductos.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(btnReporteProductos);

        // Acciones de los botones
        btnReporteUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteUsuarios();
            }
        });

        btnReporteProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteProductos();
            }
        });
    }

    private void generarReporteUsuarios() {
        controlador.generarReporteUsuarios();
        JOptionPane.showMessageDialog(this, "Reporte de Usuarios generado con éxito.", "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    private void generarReporteProductos() {
        controlador.generarReporteProductos();
        JOptionPane.showMessageDialog(this, "Reporte de Productos generado con éxito.", "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VReporteVentas().setVisible(true);
            }
        });
    }
}