/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connect;

/**
 *
 * @author adhary
 */
import java.sql.*;

public class Connect {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
<<<<<<< HEAD
    String user = "root";
    String pwd = "";
    String host = "localhost";
    String db = "kosan";
=======
    String user = "u8mbgqaxoeg0zcis";
    String pwd = "FjguBHwUNDBwFYCURw9R";
    String host = "bnqpvwcz8bi4g2dv8co1-mysql.services.clever-cloud.com";
    String db = "bnqpvwcz8bi4g2dv8co1";
>>>>>>> f7ed01c (revisi tampilan oleh daffa)
    Connection con;
    Statement s;
    ResultSet rs;
    pilihan p = new pilihan();
    
    try{
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
 
            while (!con.isClosed()) {
                p.menu();
            }

            s.close();
            con.close();

        }
        catch (SQLException e)
        {
          System.out.println("Koneksi Gagal" +e.toString());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("JDBC Driver tidak ditemukan");
        }
    }   
}
