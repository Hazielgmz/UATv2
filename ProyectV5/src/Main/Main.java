package Main;

import Vista.VLogin;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VLogin loginView = new VLogin();
            loginView.setVisible(true);
        });
    }
}
//hola soy yo
