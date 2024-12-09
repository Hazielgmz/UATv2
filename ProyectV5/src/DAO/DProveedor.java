package DAO;

import Librerias.ConexionBD;
import Modelo.MProveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DProveedor {

    public boolean agregarProveedor(MProveedor proveedor) {
        String query = "INSERT INTO PROVEEDOR (nombre, direccion, telefono, estado) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConexionBD.conectarSQLServer();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getEstado());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarProveedor(MProveedor proveedor) {
        String query = "UPDATE PROVEEDOR SET nombre = ?, direccion = ?, telefono = ?, estado = ? WHERE ProveedorID = ?";
        try (Connection connection = ConexionBD.conectarSQLServer();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getEstado());
            statement.setInt(5, proveedor.getProveedorID());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarProveedor(int proveedorID) {
        String query = "DELETE FROM PROVEEDOR WHERE ProveedorID = ?";
        try (Connection connection = ConexionBD.conectarSQLServer();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, proveedorID);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MProveedor> listarProveedores() {
        List<MProveedor> proveedores = new ArrayList<>();
        String query = "SELECT * FROM PROVEEDOR";
        try (Connection connection = ConexionBD.conectarSQLServer();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                MProveedor proveedor = new MProveedor(
                        resultSet.getInt("ProveedorID"),
                        resultSet.getString("nombre"),
                        resultSet.getString("direccion"),
                        resultSet.getString("telefono"),
                        resultSet.getString("estado")
                );
                proveedores.add(proveedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }
}
