/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

/**
 *
 * @author adhary
 */

import java.util.Scanner;
import java.io.*;
import java.sql.*;

public class transaksi {
    String user = "root";
    String pwd  = "";
    String host = "localhost";
    String db   = "kosan";
    Scanner sc = new Scanner(System.in);
    Connection con;
    Statement s;
    ResultSet rs;
    
    public void transaksi_kost()  throws IOException, ClassNotFoundException
    {
        try{
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con  = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        //User memasukan data untuk transaksi untuk lama sewa
        System.out.print("Masukkan Nomor Kamar = ");
        int ip = sc.nextInt();
        System.out.print("Masukkan Tanggal (T-B-H) = ");
        String tanggal = sc.next().trim();
        System.out.print("Masukkan Lama Sewa = ");
        int lama = sc.nextInt();
    
           //mengambil nama penghuni dari tabel penghuni dengan relasi no kamar
        String sql_nokamar = "SELECT nama_penghuni FROM t_penghuni WHERE no_kamar = '%s' ";
        sql_nokamar = String.format(sql_nokamar, ip);
        
                
        rs = s.executeQuery(sql_nokamar);
        rs.next();
        String namap = rs.getString("nama_penghuni");
        
     
        String jkamar = null;
        int hbulan = 0;
        
        if (ip > 0 && ip <= 10 )
        {
            jkamar = "Ekonomi";
             hbulan = 1000000;
        }
        else if (ip >= 11 && ip <= 20 )
        {
            jkamar = "Standar";
            hbulan = 2000000;
        }
        else if (ip >= 21 && ip <= 30 )
        {
            jkamar = "Atas";
            hbulan = 3000000;
        }
        else {
            jkamar = "Tidak Ada";
            hbulan = 0;
        }
        
        int tot = lama * hbulan;
        
        
        //query tambah data pada tabel transaksi
        String sql = "INSERT INTO t_transaksi (no_kamar, jenis_kamar, nama_penghuni ,tanggal_transaksi,lama_sewa,harga_bulan,total) "
                + "VALUES ('%s','%s','%s','%s','%d','%d','%d')";
        sql = String.format(sql, ip, jkamar, namap, tanggal,  lama, hbulan, tot); 
      
     
        s.execute(sql);
        System.out.println("");
        System.out.println("Transaksi Berhasil");
        
        //Close Statement dan Conn
        con.close();
        s.close();
        
        }
        
        // Error
        catch (SQLException e)
        {
          System.out.println("Koneksi Gagal" +e.toString());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("JDBC Driver tidak ditemukan");
        }
    }
    
    // pilihan menampilkan transaksi
    public void tampilTransaksi() throws ClassNotFoundException, SQLException {
       
    try {
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
        String sql = "SELECT * FROM t_transaksi";
        
        rs = s.executeQuery(sql);
       
         
            System.out.println("+----------------------------------------------------+");
            System.out.println("|\t\t\tDATA TRANSAKSI KOST\t\t\t     |");
            System.out.println("+----------------------------------------------------+");

            while (rs.next()) {
                
                int id = rs.getInt("id_transaksi");
                String jkamar = rs.getString("jenis_kamar");
                String ip = rs.getString("no_kamar");
                String namap = rs.getString("nama_penghuni");
                String tanggal = rs.getString("tanggal_transaksi");
                int lama = rs.getInt("lama_sewa");
                int hbulan = rs.getInt("harga_bulan");
                int tot = rs.getInt("total");
                
                
                System.out.println(String.format("%-4s%-6s%-10s%-14s%-14s%-4s%-12s%-4s"
                        ,id, ip, jkamar, namap,tanggal, lama, hbulan, tot));
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
    
    // menu pilihan untuk melihat fasilitas kamar kost
    public void fasilitas_kost()  throws IOException, ClassNotFoundException
    {
        try {
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
        String sql = "SELECT no_kamar,nama_penghuni, fasilitas,harga_bulan "
                + "FROM t_transaksi "
                + "INNER JOIN t_kamar "
                + "ON jenis_kamar = jenis_kamar";
        
        rs = s.executeQuery(sql);
       
         
            System.out.println("+----------------------------------------------------+");
            System.out.println("|\t\tDATA FASILITAS KOST\t\t     |");
            System.out.println("+----------------------------------------------------+");

            while (rs.next()) {
                
                int ip = rs.getInt("no_kamar");
                String namap = rs.getString("nama_penghuni");
                String jkamar = rs.getString("jenis_kamar");
                String fas = rs.getString("fasilitas");
                int hbulan = rs.getInt("harga_bulan");
              
                
                
                System.out.println(String.format("%-4s%-14s%-10s%-14s%-12s"
                        , ip, namap,jkamar, fas, hbulan));
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
