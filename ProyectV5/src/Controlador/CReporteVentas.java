package Controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Librerias.ConexionBD;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CReporteVentas {

    // Método para obtener la conexión
    private Connection conectarSQLServer() {
        return ConexionBD.conectarSQLServer(); // Llama al método estático para manejar la conexión
    }

    // Método para generar el reporte de usuarios
    public void generarReporteUsuarios() {
        String rutaArchivo = "reporte_usuarios.pdf";
        Document documento = new Document();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();

            // Título del reporte
            Paragraph titulo = new Paragraph("Reporte de Usuarios", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" ")); // Espacio en blanco

            // Conectar a la base de datos
            conexion = conectarSQLServer();
            statement = conexion.createStatement();
            String consultaSQL = "SELECT Nombre, ApellidoPaterno, email, direccion, telefono, usuario, clave FROM EMPLEADO";
            resultSet = statement.executeQuery(consultaSQL);

            // Crear la tabla de usuarios
            PdfPTable tabla = new PdfPTable(7); // 7 columnas
            tabla.addCell("Nombre");
            tabla.addCell("Apellido Paterno");
            tabla.addCell("Email");
            tabla.addCell("Dirección");
            tabla.addCell("Teléfono");
            tabla.addCell("Usuario");
            tabla.addCell("Clave");

            boolean hayDatos = false;

            // Agregar filas a la tabla
            while (resultSet.next()) {
                tabla.addCell(resultSet.getString("Nombre"));
                tabla.addCell(resultSet.getString("ApellidoPaterno"));
                tabla.addCell(resultSet.getString("email"));
                tabla.addCell(resultSet.getString("direccion"));
                tabla.addCell(resultSet.getString("telefono"));
                tabla.addCell(resultSet.getString("usuario"));
                tabla.addCell(resultSet.getString("clave"));
                hayDatos = true;
            }

            if (!hayDatos) {
                Paragraph sinDatos = new Paragraph("No hay usuarios registrados.", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
                sinDatos.setAlignment(Element.ALIGN_CENTER);
                documento.add(sinDatos);
            } else {
                documento.add(tabla);
            }

            System.out.println("Reporte de usuarios generado en " + rutaArchivo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
                if (documento != null && documento.isOpen()) documento.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método para generar el reporte de productos
    public void generarReporteProductos() {
        String rutaArchivo = "reporte_productos.pdf";
        Document documento = new Document();

        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();

            // Título del reporte
            Paragraph titulo = new Paragraph("Reporte de Productos", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" ")); // Espacio en blanco

            // Conectar a la base de datos
            conexion = conectarSQLServer();
            statement = conexion.createStatement();
            String consultaSQL = """
                SELECT 
                    P.nombre_producto,
                    PR.nombre AS nombre_proveedor,
                    P.stock,
                    P.PrecioUnitario,
                    P.costo,
                    P.tipo
                FROM 
                    dbo.PRODUCTO P
                INNER JOIN 
                    dbo.PROVEEDOR PR
                ON 
                    P.ProveedorID = PR.ProveedorID
            """;
            resultSet = statement.executeQuery(consultaSQL);

            // Crear la tabla de productos
            PdfPTable tabla = new PdfPTable(6); // 6 columnas
            tabla.addCell("Nombre del Producto");
            tabla.addCell("Proveedor");
            tabla.addCell("Stock Disponible");
            tabla.addCell("Precio Unitario");
            tabla.addCell("Costo");
            tabla.addCell("Tipo");

            boolean hayDatos = false;

            // Agregar filas a la tabla
            while (resultSet.next()) {
                tabla.addCell(resultSet.getString("nombre_producto"));
                tabla.addCell(resultSet.getString("nombre_proveedor"));
                tabla.addCell(resultSet.getString("stock"));
                tabla.addCell(resultSet.getString("PrecioUnitario"));
                tabla.addCell(resultSet.getString("costo"));
                tabla.addCell(resultSet.getString("tipo"));
                hayDatos = true;
            }

            if (!hayDatos) {
                Paragraph sinDatos = new Paragraph("No hay productos registrados.", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
                sinDatos.setAlignment(Element.ALIGN_CENTER);
                documento.add(sinDatos);
            } else {
                documento.add(tabla);
            }

            System.out.println("Reporte de productos generado en " + rutaArchivo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
                if (documento != null && documento.isOpen()) documento.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
