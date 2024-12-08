package Librerias;

import java.sql.*;


public class ConexionBD {
	// Variables para la conexi�n a MySQL
    private static final String urlMySQL = "jdbc:mysql://";
    private static final String usuarioMySQL = "tu_usuario_mysql";
    private static final String passwordMySQL = "tu_contraseña_mysql";

    // Variables para la conexi�n a PostgreSQL
    private static final String urlPostgreSQL = "jdbc:postgresql://";
    private static final String usuarioPostgreSQL = "tu_usuario_postgresql";
    private static final String passwordPostgreSQL = "tu_contraseña_postgresql";

    // Variables para la conexi�n a MariaDB
    private static final String urlMariaDB = "jdbc:mariadb://";
    private static final String usuarioMariaDB = "tu_usuario_mariadb";
    private static final String passwordMariaDB = "tu_contraseña_mariadb";

    // Variables para la conexi�n a Oracle
    private static final String urlOracle = "jdbc:oracle:thin:@";
    private static final String usuarioOracle = "tu_usuario_oracle";
    private static final String passwordOracle = "tu_contraseña_oracle";

    // Variables para la conexi�n a Access utilizando UCanAccess
    private static final String urlAccess = "jdbc:ucanaccess://C:/Users/hazie/eclipse-workspace/LaBuena/POSBD.accdb";

    // Variables para la conexi�n a SQL Server 
    private static final String urlSQLServer = "jdbc:sqlserver://";
    private static final String usuarioSQLServer = "tu_usuario_sqlserver";
    private static final String passwordSQLServer = "tu_contraseña_sqlserver";

    // M�todo para conectar a SQL Server Express
    public static Connection conectarSQLServer() {
        try {
            return DriverManager.getConnection(urlSQLServer, usuarioSQLServer, passwordSQLServer);
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
    // M�todo para conectar a MySQL
    public static Connection conectarMySQL(String[] parametros) throws SQLException {
        String cadenaconexion = urlMySQL + parametros[0] + "/" + parametros[1];
        Connection conexion = DriverManager.getConnection(cadenaconexion, parametros[2], parametros[3]);
        return conexion;
    }
    // M�todo para conectar a PostgreSQL
    public static Connection conectarPostgreSQL(String[] parametros) throws SQLException {
        String cadenaconexion = urlPostgreSQL + parametros[0] + ":" + parametros[1] + "/" + parametros[2];
        Connection conexion = DriverManager.getConnection(cadenaconexion, parametros[3], parametros[4]);
        return conexion;
    }
    // M�todo para conectar a MariaDB
    public static Connection conectarMariaDB(String[] parametros) throws SQLException {
        String cadenaconexion = urlMariaDB + parametros[0] + ":" + parametros[1] + "/" + parametros[2];
        Connection conexion = DriverManager.getConnection(cadenaconexion, parametros[3], parametros[4]);
        return conexion;
    }
    // M�todo para conectar a Oracle
    public static Connection conectarOracle(String[] parametros) throws SQLException {
        String cadenaconexion = urlOracle + parametros[0] + ":" + parametros[1] + ":" + parametros[2];
        Connection conexion = DriverManager.getConnection(cadenaconexion, parametros[3], parametros[4]);
        return conexion;
    }
    // M�todo para conectar a UCanAccess
    public static Connection conectarUCanAccess(String parametros) throws SQLException {  
        String cadenaconexion = urlAccess + parametros;
        System.out.println(cadenaconexion);
        Connection conexion = DriverManager.getConnection(cadenaconexion);
        return conexion;
    }
}
