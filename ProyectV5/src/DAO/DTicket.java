package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Librerias.ConexionBD;
import Modelo.MTicket;

public class DTicket {

    // Método para insertar un ticket
    public boolean insertarTicket(MTicket ticket) {
        String query = "INSERT INTO TICKET (EmpleadoID, ProductoID, Cantidad, PrecioUnitario, Subtotal, Total, Fecha, Descuento) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Eliminar OrdenID de la consulta
        try (Connection conn = ConexionBD.conectarSQLServer();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, ticket.getEmpleadoID());
            stmt.setInt(2, ticket.getProductoID());
            stmt.setBigDecimal(3, ticket.getCantidad());
            stmt.setBigDecimal(4, ticket.getPrecioUnitario());
            stmt.setBigDecimal(5, ticket.getSubtotal());
            stmt.setBigDecimal(6, ticket.getTotal());
            stmt.setTimestamp(7, Timestamp.valueOf(ticket.getFecha()));
            stmt.setBigDecimal(8, ticket.getDescuento());
    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar el ticket: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    

    // Método para obtener todos los tickets
    public List<MTicket> obtenerTickets() {
        List<MTicket> tickets = new ArrayList<>();
        String query = "SELECT * FROM TICKET";
        try (Connection conn = ConexionBD.conectarSQLServer();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                MTicket ticket = new MTicket();
                ticket.setOrdenID(rs.getInt("OrdenID"));
                ticket.setEmpleadoID(rs.getInt("EmpleadoID"));
                ticket.setProductoID(rs.getInt("ProductoID"));
                ticket.setCantidad(rs.getBigDecimal("Cantidad"));
                ticket.setPrecioUnitario(rs.getBigDecimal("PrecioUnitario"));
                ticket.setSubtotal(rs.getBigDecimal("Subtotal"));
                ticket.setTotal(rs.getBigDecimal("Total"));
                ticket.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                ticket.setDescuento(rs.getBigDecimal("Descuento"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los tickets: " + e.getMessage());
        }
        return tickets;
    }

    // Método para eliminar un ticket por OrdenID
    public boolean eliminarTicket(int ordenID) {
        String query = "DELETE FROM TICKET WHERE OrdenID = ?";
        try (Connection conn = ConexionBD.conectarSQLServer();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ordenID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el ticket: " + e.getMessage());
            return false;
        }
    }
}
