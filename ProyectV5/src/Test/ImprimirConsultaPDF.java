package Test;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImprimirConsultaPDF {
    public static void main(String[] args) {
        // Nombre del archivo PDF
        String archivoPDF = "empleados.pdf";

        // Datos de conexión a la base de datos
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PosDB;encrypt=true;trustServerCertificate=true";
        String usuario = "sa";
        String contraseña = "3312";

        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. Conectar a la base de datos
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a la base de datos");

            // 2. Crear una consulta SQL
            String consultaSQL = "SELECT EmpleadoID, Nombre, ApellidoPaterno, email, direccion, telefono, usuario, clave FROM EMPLEADO";

            statement = conexion.createStatement();
            resultSet = statement.executeQuery(consultaSQL);

            // 3. Crear un documento PDF
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(archivoPDF));
            documento.open();

            // 4. Agregar título al PDF
            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Paragraph titulo = new Paragraph("Reporte de Empleados", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            // Espacio después del título
            documento.add(new Paragraph(" "));

            // 5. Crear una tabla para mostrar los datos
            PdfPTable tabla = new PdfPTable(8); // 8 columnas
            tabla.setWidthPercentage(100);

            // 6. Crear la cabecera de la tabla
            String[] encabezados = {
                "EmpleadoID", "Nombre", "Apellido Paterno", "Email", 
                "Dirección", "Teléfono", "Usuario", "Clave"
            };
            for (String encabezado : encabezados) {
                PdfPCell celda = new PdfPCell(new Phrase(encabezado));
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tabla.addCell(celda);
            }

            // 7. Rellenar la tabla con los datos de la consulta
            while (resultSet.next()) {
                tabla.addCell(resultSet.getString("EmpleadoID"));
                tabla.addCell(resultSet.getString("Nombre"));
                tabla.addCell(resultSet.getString("ApellidoPaterno"));
                tabla.addCell(resultSet.getString("email"));
                tabla.addCell(resultSet.getString("direccion"));
                tabla.addCell(resultSet.getString("telefono"));
                tabla.addCell(resultSet.getString("usuario"));
                tabla.addCell(resultSet.getString("clave"));
            }

            // 8. Agregar la tabla al documento
            documento.add(tabla);

            // 9. Cerrar el documento PDF
            documento.close();
            System.out.println("El PDF se ha creado con éxito: " + archivoPDF);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
