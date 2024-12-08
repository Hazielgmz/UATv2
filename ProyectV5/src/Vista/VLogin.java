package Vista;

import Controlador.CLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VLogin extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnSalir;
    private JButton btnRegister;

    public VLogin() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\hazie\\eclipse-workspace\\Proyecto_final\\src\\2331680.png"));
        setTitle("Inicio de Sesión");
        setSize(435, 259);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setLayout(new BorderLayout(10, 10));

        // Panel superior con el icono y mensaje
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel lblIcono = new JLabel(new ImageIcon("icono-caja-registradora.png")); // Cambia "icono-caja-registradora.png" a la ruta de tu icono
        JLabel lblMensaje = new JLabel("Por favor ingresa tu usuario y contraseña para continuar");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        panelSuperior.add(lblIcono, BorderLayout.WEST);
        panelSuperior.add(lblMensaje, BorderLayout.CENTER);

        // Panel central para los campos de usuario y contraseña
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelCampos.add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        panelCampos.add(txtUsername);

        panelCampos.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panelCampos.add(txtPassword);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnLogin = new JButton("Aceptar");
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        btnSalir = new JButton("Salir");
        btnSalir.setBackground(Color.LIGHT_GRAY);
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setFocusPainted(false);

        btnRegister = new JButton("Registrarse");
        btnRegister.setBackground(new Color(100, 149, 237));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);

        panelBotones.add(btnLogin);
        panelBotones.add(btnRegister);
        panelBotones.add(btnSalir);

        // Añadir los paneles al panel principal
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(panelCampos, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        getContentPane().add(panel);
        
        CLogin controlador = new CLogin(this);
        controlador.initController();
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }
}
