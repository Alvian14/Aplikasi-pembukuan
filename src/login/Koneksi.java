package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class Koneksi {
    Connection con;
    Statement pst;
    
    
    
    private static Connection mysqlconfig;
    
    public static Connection configDB() throws SQLException {
        try{
            String url = "jdbc:mysql://localhost:3306/umkm_youn_helm";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi berhasil;");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
        }
        return mysqlconfig;
    }
    
    
}


   