package Vista;

import Controlador.CAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VAdmin extends JFrame {

    private JPanel contentPane;
    private JMenuBar menuBar;
    private CAdmin controlador;

    public VAdmin() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\josea\\eclipse-workspace\\Proyecto_final\\src\\2331680.png"));
        setTitle("Sistema de Punto de Venta - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuracion de pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); // Remueve los bordes para pantalla completa

        // Configuracion de la barra de menu
        menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setJMenuBar(menuBar);

        // Configuracion del contenido principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setLayout(new BorderLayout(10, 10));

        // Titulo de bienvenida
        JLabel lblTitulo = new JLabel("POS Admin", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.ITALIC, 26));
        lblTitulo.setForeground(new Color(30, 144, 255));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel central con botones principales
        JPanel panelBotones = new JPanel(new GridLayout(2, 5, 15, 15));
        panelBotones.setBackground(new Color(245, 245, 250));

        // Creaci�n de botones
        JButton btnCorteCaja = crearBoton("Corte Caja");
        JButton btnGestionProductos = crearBoton("Gestion de Productos");
        JButton btnConsultasVentas = crearBoton("Consultas de Ventas");
        JButton btnReportes = crearBoton("Generar Reporte");
        JButton btnAyuda = crearBoton("Ayuda");
        JButton btnSalir = crearBoton("Salir", Color.RED);
        JButton btnGestionProveedores = crearBoton("Gestion de Proveedores");
        JButton btnDevoluciones = crearBoton("Devoluciones");

        // Agregar botones al panel
        panelBotones.add(btnCorteCaja);
        panelBotones.add(btnGestionProductos);
        panelBotones.add(btnConsultasVentas);
        panelBotones.add(btnReportes);
        panelBotones.add(btnAyuda);
        panelBotones.add(btnSalir);
        panelBotones.add(btnGestionProveedores);
        panelBotones.add(btnDevoluciones);

        contentPane.add(panelBotones, BorderLayout.CENTER);
        setContentPane(contentPane);

        // Inicializar controlador
        controlador = new CAdmin(this);

        // Configurar listeners
        controlador.configurarListeners(btnCorteCaja, btnGestionProductos, btnConsultasVentas, 
                                         btnReportes, btnAyuda, btnSalir, btnGestionProveedores, btnDevoluciones);

        // Configurar men�
        controlador.configurarMenu(menuBar);
    }

    // Metodo auxiliar para crear botones con estilo
    private JButton crearBoton(String texto) {
        return crearBoton(texto, new Color(30, 144, 255));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return boton;
    }
}
