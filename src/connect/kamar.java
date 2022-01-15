/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

import java.util.Scanner;
import java.io.*;
import java.sql.*;

/**
 *
 * @author adhary
 */
public class kamar {
    
    String user = "root";
    String pwd  = "";
    String host = "localhost";
    String db   = "kosan";
    Scanner sc = new Scanner(System.in);
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Connection con;
    Statement s;
    ResultSet rs;
    
    
      // menu pilihan untuk melihat keterangan kost
    public void ket_kost()  throws IOException, ClassNotFoundException
    {
        try {
         //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
        String sql = "SELECT * FROM t_kamar";
        
        rs = s.executeQuery(sql);
       
         
            System.out.println("************************************************************************");
            System.out.println("\t\t\tData Keterangan Kamar Kost\t\t\t");
            System.out.println("************************************************************************");

            while (rs.next()) {
                
                String jkamar = rs.getString("jenis_kamar");
                String jumlah = rs.getString("jumlah_kamar");
                String ketno = rs.getString("ket_no_kamar");
                String fas = rs.getString("fasilitas");
                int hbulan = rs.getInt("harga_bulan");
                
                
                System.out.println(String.format("%-10s%-4s%-10s%-26s%-12s"
                        ,jkamar, jumlah, ketno, fas, hbulan));
            }
            
            // close statement & connection
            con.close();
            s.close();
        }  
        
            // error
         catch(SQLException e){
            System.out.println("Koneksi Gagal "+e.toString());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("JDBC Driver tidak ditemukan");
        }
    }
}
