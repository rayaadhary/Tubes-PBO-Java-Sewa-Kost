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
    String pwd = "";
    String host = "localhost";
    String db = "kosan";
    Scanner sc = new Scanner(System.in);
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        
        System.out.println("**************************************");
        System.out.println("\tTambah Data Transaksi\t");
        System.out.println("**************************************");
        
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
        sql = String.format(sql, ip, jkamar, namap, tanggal,  lama,  hbulan, tot); 
      
     
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
       
         
            System.out.println("************************************************************************");
            System.out.println("\t\t\tTampil Transaksi Kost\t\t\t");
            System.out.println("************************************************************************");

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
    
    // ubah data penghuni
public void ubahTransaksi() throws IOException
    {
        try{
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
         System.out.println("**************************************");
         System.out.println("\tUbah Data Transaksi\t");
         System.out.println("**************************************");
        
        // memasukkan nomor kamar yang akan diubah lama sewanya
        System.out.print("Masukkan Nomor Kamar yang Ingin diubah = ");
        String ip_baru = sc.next();
        
        //Query sql tampil yang akan di update
        String sql = "SELECT * FROM t_transaksi WHERE no_kamar='%s'";
        sql = String.format(sql, ip_baru);
        
        //Eksekusi Query
        rs = s.executeQuery(sql);
        rs.next();
        int id = rs.getInt("id_transaksi");
        String ip   = rs.getString("no_kamar");
        String jkamar = rs.getString("jenis_kamar");
        String namap = rs.getString("nama_penghuni");
        String tanggal = rs.getString("tanggal_transaksi");
        int lama = rs.getInt("lama_sewa");
        int hbulan = rs.getInt("harga_bulan");
        int tot = rs.getInt("total");
        System.out.println("**************************************");
        System.out.println("\tData Transaksi yang akan diubah\t");
        System.out.println("**************************************");
        System.out.println("Id Transaksi\t= "+id);
        System.out.println("Nomor Kamar\t= " +ip);
        System.out.println("Jenis Kamar\t= "+jkamar);
        System.out.println("Nama Penghuni\t= " +namap);
        System.out.println("Tanggal Transaksi= " +tanggal);
        System.out.println("Lama Sewa\t= " +lama);
        System.out.println("Harga Bulan\t= "+hbulan);
        System.out.println("Total\t\t= "+tot);
        
        //Memasukkan data sewa yang diubah
        System.out.println("**************************************");
        System.out.println("\tMasukkan Transaksi baru\t");
        System.out.println("**************************************");
        
        System.out.print("Tanggal Transaksi (T-B-H) = ");
        String tanggal_baru = sc.next();
        
        System.out.print("Lama Sewa = ");
        int lama_baru = sc.nextInt();
        
        tot = lama_baru * hbulan;
        
        //Query sql update
        String sqlbaru = "UPDATE t_transaksi SET  tanggal_transaksi ='%s', lama_sewa='%d', total='%d'  WHERE no_kamar='%s'";
        sqlbaru = String.format(sqlbaru, tanggal_baru, lama_baru, tot, ip_baru);
        
        //Eksekusi Query Update
        s.execute(sqlbaru);
        System.out.println("Ubah Transaksi Berhasil");
        
        //Close Statement dan Conn
        con.close();
        s.close();
        
        //Eror
        }
        catch (SQLException e)
        {
          System.out.println("Data No Kamar yang diinputkan tidak ada" +e.toString());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("JDBC Driver tidak ditemukan");
        }
    }
    
    // cari transaksi berdasarkan nomor kamar
  public void cariTransaksiNo() throws IOException
    {
        
        try{
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        //User memasukkan no kamar yang ingin dicari
        System.out.print("Masukkan Nomor Kamar yang dicari = ");
        int ip_cari = sc.nextInt();
        
        //Query sql tampil yang akan di update
        String sql = "SELECT * FROM t_transaksi WHERE no_kamar='%s'";
        sql = String.format(sql, ip_cari);
        
        //Eksekusi Query
        rs = s.executeQuery(sql);
        if (rs.next())
        {
            int id = rs.getInt("id_transaksi");
            int ip   = rs.getInt("no_kamar");
            String jkamar = rs.getString("jenis_kamar");
            String namap = rs.getString("nama_penghuni");
            String tanggal = rs.getString("tanggal_transaksi");
            int lama = rs.getInt("lama_sewa");
            int hbulan = rs.getInt("harga_bulan");
            int tot = rs.getInt("total");
            System.out.println("**************************************");
            System.out.println("\tData Transaksi yang Dicari\t");
            System.out.println("**************************************");
            System.out.println("Id Transaksi\t= "+id);
            System.out.println("Nomor Kamar\t= " +ip);
            System.out.println("Jenis Kamar\t= "+jkamar);
            System.out.println("Nama Penghuni\t= " +namap);
            System.out.println("Tanggal Transaksi= " +tanggal);
            System.out.println("Lama Sewa\t= " +lama);
            System.out.println("Harga Bulan\t= "+hbulan);
            System.out.println("Total\t\t= "+tot);
        }
        else
        {
            System.out.println("Data Transaksi tidak ditemukan!");
        }
        
        //Close Statement dan Conn
        con.close();
        s.close();
        
        //Error
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
 

 
 // cari data transaksi berdasarkan nama
  public void cariTransaksiNama() throws IOException
    {
        try{
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        //User memasukkan penghuni yang ingin dicari
        System.out.print("Masukkan Nama Penghuni yang dicari = ");
        String namap_cari = br.readLine();
        
        //Query sql tampil yang akan di update
        String sql = "SELECT * FROM t_transaksi WHERE nama_penghuni='%s'";
        sql = String.format(sql, namap_cari);
        
        //Eksekusi Query
        rs = s.executeQuery(sql);
        if (rs.next())
        {
            int id = rs.getInt("id_transaksi");
            int ip   = rs.getInt("no_kamar");
            String jkamar = rs.getString("jenis_kamar");
            String namap = rs.getString("nama_penghuni");
            String tanggal = rs.getString("tanggal_transaksi");
            int lama = rs.getInt("lama_sewa");
            int hbulan = rs.getInt("harga_bulan");
            int tot = rs.getInt("total");
            System.out.println("**************************************");
            System.out.println("\tData Transaksi yang Dicari\t");
            System.out.println("**************************************");
            System.out.println("Id Transaksi\t= "+id);
            System.out.println("Nomor Kamar\t= " +ip);
            System.out.println("Jenis Kamar\t= "+jkamar);
            System.out.println("Nama Penghuni\t= " +namap);
            System.out.println("Tanggal Transaksi= " +tanggal);
            System.out.println("Lama Sewa\t= " +lama);
            System.out.println("Harga Bulan\t= "+hbulan);
            System.out.println("Total\t\t= "+tot);
        }
        else
        {
            System.out.println("Data Transaksi tidak ditemukan!");
        }
        
        //Close Statement dan Conn
        con.close();
        s.close();
        
        //Error
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
  
  // hapus transaksi 
 public void hapusTransaksi() throws IOException, ClassNotFoundException{
    
        try {
        //koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
         System.out.println("**************************************");
         System.out.println("\tHapus Data Transaksi \t");
         System.out.println("**************************************");
        
        
        // ambil input dari user
        System.out.print("Nomor Kamar yang mau dihapus =  ");
      
        String ip = sc.next().trim();
        
        // buat query hapus
        String sql = String.format("DELETE FROM t_transaksi WHERE no_kamar='%s'",ip);
       
        // hapus data
        s.execute(sql);
        
        //System.out.println("Data telah terhapus...");
        
           int intBaris =  s.executeUpdate(sql);
            if(intBaris > 0)
            {
                System.out.println("Data transaksi telah terhapus");
            }
            else
            {
                System.out.println("Data No Kamar yang diinputkan tidak ada");
            } 
        
        //Close Statement dan Conn
        con.close();
        s.close();
    } 
  catch(SQLException e)
  {
            System.out.println("Koneksi Gagal "+e.toString());
   }
   catch (ClassNotFoundException e)
        {
            System.out.println("JDBC Driver tidak ditemukan");
        }
} 
} 

