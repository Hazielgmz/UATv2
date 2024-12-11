package DAO;

import Librerias.ConexionBD;
import Modelo.MProducto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DProducto {

    public boolean agregarProducto(MProducto producto) {
        String query = "INSERT INTO PRODUCTO (codigo_barras, nombre_producto, ProveedorID, stock, PrecioUnitario, costo, tipo) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConexionBD.conectarSQLServer();
             PreparedStatement statement = connection.prepareStatement(query)) {
    
            if (producto.getCodigoBarras() == null || producto.getCodigoBarras().trim().isEmpty()) {
                statement.setNull(1, java.sql.Types.VARCHAR);
            } else {
                statement.setString(1, producto.getCodigoBarras());
            }
    
            statement.setString(2, producto.getNombreProducto());
            statement.setInt(3, producto.getProveedorID());
            statement.setBigDecimal(4, producto.getStock());
            statement.setBigDecimal(5, producto.getPrecioUnitario());
            statement.setBigDecimal(6, producto.getCosto());
            statement.setString(7, producto.getTipo());
    
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean modificarProducto(MProducto producto) {
        String query = "UPDATE PRODUCTO SET codigo_barras = ?, nombre_producto = ?, ProveedorID = ?, stock = ?, " +
                       "PrecioUnitario = ?, costo = ?, tipo = ? WHERE CodigoID = ?";
        try (Connection connection = ConexionBD.conectarSQLServer();
             PreparedStatement statement = connection.prepareStatement(query)) {
    
            if (producto.getCodigoBarras() == null || producto.getCodigoBarras().trim().isEmpty()) {
                statement.setNull(1, java.sql.Types.VARCHAR);
            } else {
                statement.setString(1, producto.getCodigoBarras());
            }
    
            statement.setString(2, producto.getNombreProducto());
            statement.setInt(3, producto.getProveedorID());
            statement.setBigDecimal(4, producto.getStock());
            statement.setBigDecimal(5, producto.getPrecioUnitario());
            statement.setBigDecimal(6, producto.getCosto());
            statement.setString(7, producto.getTipo());
            statement.setInt(8, producto.getCodigoID());
    
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarProducto(int codigoID) {
        String query = "DELETE FROM PRODUCTO WHERE CodigoID = ?";
        try (Connection connection = ConexionBD.conectarSQLServer();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, codigoID);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MProducto> listarProductos() {
        List<MProducto> productos = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTO";
        try (Connection connection = ConexionBD.conectarSQLServer();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                MProducto producto = new MProducto(
                        resultSet.getInt("CodigoID"),
                        resultSet.getString("codigo_barras"),
                        resultSet.getString("nombre_producto"),
                        resultSet.getInt("ProveedorID"),
                        resultSet.getBigDecimal("stock"),
                        resultSet.getBigDecimal("PrecioUnitario"),
                        resultSet.getBigDecimal("costo"),
                        resultSet.getString("tipo")
                );
                productos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public List<String> obtenerProveedores() {
        List<String> proveedores = new ArrayList<>();
        String query = "SELECT ProveedorID, nombre FROM PROVEEDOR";
        try (Connection connection = ConexionBD.conectarSQLServer();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String proveedor = resultSet.getInt("ProveedorID") + " - " + resultSet.getString("nombre");
                proveedores.add(proveedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }
    
    public boolean existeCodigoBarras(String codigoBarras, int codigoID) {
        if (codigoBarras == null || codigoBarras.trim().isEmpty()) return false;
        String query = "SELECT COUNT(*) FROM PRODUCTO WHERE codigo_barras = ? AND CodigoID != ?";
        try (Connection connection = ConexionBD.conectarSQLServer();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codigoBarras);
            statement.setInt(2, codigoID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public MProducto buscarProductoPorCodigoBarras(String codigoBarras) {
        String query = "SELECT * FROM PRODUCTO WHERE codigo_barras = ?";
        try (Connection connection = ConexionBD.conectarSQLServer();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, codigoBarras);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new MProducto(
                        resultSet.getInt("CodigoID"),
                        resultSet.getString("codigo_barras"),
                        resultSet.getString("nombre_producto"),
                        resultSet.getInt("ProveedorID"),
                        resultSet.getBigDecimal("stock"),
                        resultSet.getBigDecimal("PrecioUnitario"),
                        resultSet.getBigDecimal("costo"),
                        resultSet.getString("tipo")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el producto
    }
    public List<MProducto> obtenerTodosLosProductos() {
        List<MProducto> productos = new ArrayList<>();
        String query = "SELECT CodigoID, nombre_producto, costo FROM PRODUCTO";

        try (Connection conn = ConexionBD.conectarSQLServer();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MProducto producto = new MProducto();
                producto.setCodigoID(rs.getInt("CodigoID"));
                producto.setNombreProducto(rs.getString("nombre_producto"));
                producto.setCosto(rs.getBigDecimal("costo"));

                productos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    public List<MProducto> obtenerProductosPorTipo(String tipo) {
        List<MProducto> productos = new ArrayList<>();
        String query = "SELECT CodigoID, nombre_producto, costo FROM PRODUCTO WHERE tipo = ?";
    
        try (Connection conn = ConexionBD.conectarSQLServer();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MProducto producto = new MProducto();
                    producto.setCodigoID(rs.getInt("CodigoID"));
                    producto.setNombreProducto(rs.getString("nombre_producto"));
                    producto.setCosto(rs.getBigDecimal("costo"));
                    productos.add(producto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
    
}