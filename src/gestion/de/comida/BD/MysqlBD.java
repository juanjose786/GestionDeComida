package gestion.de.comida.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author juan_
 */
public class MysqlBD {
    
    private static Connection conn;
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/comidas";
    private final String user = "root";
    private final String password = "";

    public  Connection conectar() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if(conn!=null){
                System.out.println("Conexion establecida :D en: " + this.toString());
            }
            return conn;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
            return null;
        }
    }
}
