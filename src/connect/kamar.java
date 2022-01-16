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
    
    
    
    // pilihan menampilkan data kamar dan fasilitas
public void tampilKamar() throws ClassNotFoundException, SQLException {
       
    try {
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
          
       String sql = "SELECT t1.`nama_penghuni` , t2.`jenis_kamar`, t2.`fasilitas` , t2.`harga_bulan` , t1.`tanggal_transaksi` "
               + "FROM `t_transaksi` t1 "
               + "INNER JOIN t_kamar t2 "
               + "ON t1.`jenis_kamar` = t2.`jenis_kamar`";
      
        rs = s.executeQuery(sql);
       
         
            System.out.println("************************************************************************");
            System.out.println("\t\t\tTampil Kamar Kost\t\t\t");
            System.out.println("************************************************************************");

            while (rs.next()) {
                
                String namap = rs.getString("nama_penghuni");
                String jkamar = rs.getString("jenis_kamar");
                String fas = rs.getString("fasilitas");
                int hbulan = rs.getInt("harga_bulan");
                String tanggal = rs.getString("tanggal_transaksi");
              
                System.out.println(String.format("%-14s%-10s%-18s%-12s%-14s"
                        ,namap, jkamar, fas, hbulan,tanggal));
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
