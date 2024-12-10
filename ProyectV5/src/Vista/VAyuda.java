package Vista;

import javax.swing.*;
import java.awt.*;

public class VAyuda extends JFrame {

    public VAyuda() {
        setTitle("Ayuda");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        JLabel lblAyuda = new JLabel(
            "<html><h2>Ayuda del Sistema de Punto de Venta</h2><p>Para asistencia, contacta al soporte o revisa la documentacion.</p></html>",
            SwingConstants.CENTER
        );
        lblAyuda.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lblAyuda, BorderLayout.CENTER);
        add(panel);
    }

 
}
