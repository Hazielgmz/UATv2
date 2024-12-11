package Reporte;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.swing.*;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;

public class RTicket {

    public static void generarTicketPDF(String metodoPago, double total, List<String> carritoProductos) {
        Document document = new Document();
        try {
            String filePath = "ticket.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Fuentes personalizadas
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(0, 51, 102)); // Azul oscuro
            Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            Font totalFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.RED); // Color rojo para el total
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE); // Texto blanco para encabezados

            // Encabezado del ticket
            Paragraph title = new Paragraph("TICKET DE COMPRA\n", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            Paragraph subtitle = new Paragraph("¡Gracias por elegirnos!\n", subtitleFont);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            subtitle.setSpacingAfter(20);
            document.add(subtitle);

            // Información de fecha y método de pago
            Paragraph info = new Paragraph(
                "Fecha: " + LocalDateTime.now() + "\n" +
                "Método de Pago: " + metodoPago + "\n",
                normalFont
            );
            info.setSpacingAfter(10);
            document.add(info);

            // Línea divisoria decorativa
            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setLineColor(new BaseColor(0, 51, 102)); // Línea azul oscuro
            document.add(new Chunk(lineSeparator));

            // Tabla de productos
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            // Configurar anchos de columna
            float[] columnWidths = {3f, 1f};
            table.setWidths(columnWidths);

            // Encabezados de la tabla con fondo azul
            PdfPCell productHeader = new PdfPCell(new Phrase("Producto", headerFont));
            productHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            productHeader.setBackgroundColor(new BaseColor(0, 51, 102)); // Azul oscuro
            productHeader.setPadding(10);

            PdfPCell priceHeader = new PdfPCell(new Phrase("Precio", headerFont));
            priceHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            priceHeader.setBackgroundColor(new BaseColor(0, 51, 102)); // Azul oscuro
            priceHeader.setPadding(10);

            table.addCell(productHeader);
            table.addCell(priceHeader);

            // Agregar productos a la tabla
            for (String productDetails : carritoProductos) {
                String[] details = productDetails.split(" - \\$");
                PdfPCell productCell = new PdfPCell(new Phrase(details[0], normalFont));
                PdfPCell priceCell = new PdfPCell(new Phrase("$" + details[1], normalFont));

                productCell.setPadding(8);
                priceCell.setPadding(8);

                productCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

                productCell.setBackgroundColor(new BaseColor(230, 240, 255)); // Fondo azul claro
                priceCell.setBackgroundColor(new BaseColor(230, 240, 255)); // Fondo azul claro

                table.addCell(productCell);
                table.addCell(priceCell);
            }

            document.add(table);

            // Total de la compra
            Paragraph totalParagraph = new Paragraph("\nTotal: $" + String.format("%.2f", total), totalFont);
            totalParagraph.setAlignment(Element.ALIGN_RIGHT);
            totalParagraph.setSpacingBefore(10);
            totalParagraph.setSpacingAfter(10);
            document.add(totalParagraph);

            // Línea divisoria decorativa
            document.add(new Chunk(lineSeparator));

            // Mensaje final
            Paragraph footer = new Paragraph(
                "¡Esperamos verte pronto!\n" +
                "Atentamente,\n" +
                "Equipo de [Nombre de la Tienda]",
                subtitleFont
            );
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(20);
            footer.setSpacingAfter(10);
            document.add(footer);

            document.close();

            JOptionPane.showMessageDialog(null, "El ticket se generó correctamente en: " + filePath, "Ticket Generado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar el ticket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
