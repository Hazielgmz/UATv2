package Controlador;

import Vista.VInventarioGeneral;

import javax.swing.*;
import java.awt.*;

public class CInventarioGeneral {

    public void mostrarInventarioCompleto(VInventarioGeneral vista) {
        JPanel panelInventarioCompleto = crearPanelVerTodo();
        vista.getMainPanel().add(panelInventarioCompleto, BorderLayout.SOUTH);
        vista.getMainPanel().revalidate(); // Actualizar el panel para que el inventario completo sea visible
        vista.getMainPanel().repaint();
    }

    private JPanel crearPanelVerTodo() {
        JPanel panelVerTodo = new JPanel(new BorderLayout());
        panelVerTodo.setBorder(BorderFactory.createTitledBorder("Inventario Completo"));
        JTextArea textArea = new JTextArea("Aquí se mostrará todo el inventario...");
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panelVerTodo.add(scrollPane, BorderLayout.CENTER);
        return panelVerTodo;
    }

    public void abrirConsultaEspecifica() {
        JOptionPane.showMessageDialog(null, "Abrir ventana de Consulta Específica.");
    }

    public void confirmarExportacion(JFrame vista) {
        int confirm = JOptionPane.showConfirmDialog(vista,
                "¿Deseas exportar el inventario?", "Confirmación de Exportación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            exportarInventario(vista);
        }
    }

    private void exportarInventario(JFrame vista) {
        JOptionPane.showMessageDialog(vista, "Inventario exportado con éxito.",
                "Exportación Completada", JOptionPane.INFORMATION_MESSAGE);
    }
}
