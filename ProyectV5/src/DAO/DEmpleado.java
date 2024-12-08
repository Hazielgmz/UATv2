package DAO;

import Librerias.ConexionBD;
import Modelo.MEmpleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DEmpleado {
    private MEmpleado usuarioActual;

    public boolean registrarUsuario(String username, String password, String email) {
        String query = "INSERT INTO usuarios (usuario, contrase�a, email) VALUES (?, ?, ?)";

        try (Connection connection = ConexionBD.conectarUCanAccess("");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean iniciarSesion(String username, String password) {
        String query = "SELECT * FROM usuarios WHERE usuario = ? AND contrase�a = ?";

        try (Connection connection = ConexionBD.conectarUCanAccess("");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuarioActual = new MEmpleado(
                            resultSet.getString("usuario"),
                            resultSet.getString("contrase�a"),
                            resultSet.getString("email")
                    );
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public MEmpleado getUsuarioActual() {
        return usuarioActual;
    }
}
