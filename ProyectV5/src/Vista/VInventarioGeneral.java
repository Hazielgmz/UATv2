package Vista;

import Controlador.CInventarioGeneral;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VInventarioGeneral extends JFrame {
    private JPanel mainPanel;
    private CInventarioGeneral controlador;

    public VInventarioGeneral(CInventarioGeneral controlador) {
        this.controlador = controlador;
        setTitle("Inventario General");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fondo de la ventana
        getContentPane().setBackground(new Color(245, 245, 250));
        setLayout(new BorderLayout(10, 10));

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 250));

        // Panel de alertas de inventario
        JPanel panelAlertas = new JPanel(new GridLayout(3, 1, 5, 5));
        panelAlertas.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder("Alertas de Inventario"),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panelAlertas.setBackground(new Color(255, 235, 238));

        JLabel alerta1 = new JLabel("Producto B - Stock bajo: 5 unidades");
        JLabel alerta2 = new JLabel("Producto C - Stock bajo: 10 unidades");
        panelAlertas.add(alerta1);
        panelAlertas.add(alerta2);

        mainPanel.add(panelAlertas, BorderLayout.NORTH);

        // Panel principal para las opciones de inventario
        JPanel panelOpciones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelOpciones.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder("Opciones de Inventario"),
                new EmptyBorder(20, 20, 20, 20)
        ));
        panelOpciones.setBackground(new Color(230, 230, 250));

        // Configuración de los botones
        JButton btnConsultaEspecifica = new JButton("Consulta Especifica");
        JButton btnVerTodo = new JButton("Ver Todo");
        JButton btnExportar = new JButton("Exportar Inventario");

        // Aplicar estilo a los botones
        JButton[] botones = {btnConsultaEspecifica, btnVerTodo, btnExportar};
        for (JButton boton : botones) {
            boton.setFont(new Font("Arial", Font.BOLD, 14));
            boton.setBackground(new Color(100, 149, 237));
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
            boton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        }

        // Añadir botones al panel
        panelOpciones.add(btnConsultaEspecifica);
        panelOpciones.add(btnVerTodo);
        panelOpciones.add(btnExportar);

        mainPanel.add(panelOpciones, BorderLayout.CENTER);
        add(mainPanel);

        // Asignar acciones a los botones mediante el controlador
        btnConsultaEspecifica.addActionListener(e -> controlador.abrirConsultaEspecifica());
        btnVerTodo.addActionListener(e -> controlador.mostrarInventarioCompleto(this));
        btnExportar.addActionListener(e -> controlador.confirmarExportacion(this));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
