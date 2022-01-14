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
    
    public void bayar_kost()  throws IOException, ClassNotFoundException
    {
        try{
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con  = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        //User memasukan data untuk transaksi pembayaran sewa kost
        System.out.print("Masukkan Nomor Kamar = ");
        int ip = sc.nextInt();
        System.out.print("Masukkan Tanggal (T-B-H) = ");
        String tanggal = sc.next().trim();
        System.out.print("Masukkan Lama Sewa = ");
        int lama = sc.nextInt();
    
           //mengambil nomor kamar dari tabel penghuni
        String sql_nokamar = "SELECT nama_penghuni FROM t_penghuni WHERE no_kamar = '%s' ";
        sql_nokamar = String.format(sql_nokamar, ip);
                
        rs = s.executeQuery(sql_nokamar);
        rs.next();
        String namap = rs.getString("nama_penghuni");
        
     
        String jkamar = null;
        
        if (ip > 0 && ip <= 10 )
        {
            jkamar = "Standar";
        }
        else if (ip >= 11 && ip <= 20 )
        {
            jkamar = "Ekonomi";
        }
        else if (ip >= 21 && ip <= 30 )
        {
            jkamar = "Atas";
        }
        else {
            jkamar = "Tidak Ada";
        }
        
        
        //query tambah data pada tabel transaksi
        String sql = "INSERT INTO t_transaksi (no_kamar, jenis_kamar, nama_penghuni ,tanggal_transaksi,lama_sewa) VALUES ('%s','%s','%s','%s','%d')";
        sql = String.format(sql, ip, jkamar, namap, tanggal,  lama); 
      
     
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
}
