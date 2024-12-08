package Controlador;

import Vista.VLogin;
import Vista.VPOSMenu;

import javax.swing.*;

import DAO.DEmpleado;

public class CLogin {

    private final VLogin vLogin;
    private final DEmpleado userController;

    public CLogin(VLogin vLogin) {
        this.vLogin = vLogin;
        this.userController = new DEmpleado();
    }

    public void initController() {
        vLogin.getBtnLogin().addActionListener(e -> handleLogin());
        vLogin.getBtnSalir().addActionListener(e -> System.exit(0));
        vLogin.getBtnRegister().addActionListener(e -> handleRegister());
    }

    private void handleLogin() {
        String username = vLogin.getTxtUsername().getText();
        String password = new String(vLogin.getTxtPassword().getPassword());

        if (userController.iniciarSesion(username, password)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesion exitoso");

            vLogin.dispose(); // Cierra la ventana de login

            // Abrir la ventana principal del punto de venta
            VPOSMenu posMenu = new VPOSMenu(userController);
            posMenu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }

    private void handleRegister() {
        String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String clave = JOptionPane.showInputDialog("Ingrese la contraseña:");
        String email = JOptionPane.showInputDialog("Ingrese el correo electronico:");

        if (userController.registrarUsuario(usuario, clave, email)) {
            JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario. Es posible que ya exista.");
        }
    }
}
