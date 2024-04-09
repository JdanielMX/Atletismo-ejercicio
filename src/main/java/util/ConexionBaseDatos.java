package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    
    public static String db="atletismo"; 
    public static String url="jdbc:mysql://localhost:3306/"+db; 
    public static String user="root";
    public static String pass="toor";
   

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
