
package Utileria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConexionSQL {

    private static String direccionIp; 
      private static String nombreBaseDeDatos;
      private static String usuario;
      private static String contrasena;
      private static String puertoServicio;
      private static ResourceBundle propiedadesBD;

    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {

        }
        if(propiedadesBD==null){
            propiedadesBD= ResourceBundle.getBundle("Properties/Entrada");
            direccionIp=propiedadesBD.getString("ip_address");
            nombreBaseDeDatos=propiedadesBD.getString("db_name");
            usuario=propiedadesBD.getString("user");
            contrasena=propiedadesBD.getString("password");
            puertoServicio=propiedadesBD.getString("service");
            
            System.out.println("Conexion exitosa");
          }
           
         return DriverManager.getConnection("jdbc:sqlserver://" +direccionIp + 
                 ":"+puertoServicio+";databaseName="+nombreBaseDeDatos+";", usuario,contrasena);
    
    
    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println(ConexionSQL.getConnection());
    }

}
