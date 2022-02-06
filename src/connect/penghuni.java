/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

/**
 *
 * @author adhary
 */
import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class penghuni {
    

    String user = "root";
    String pwd = "";
    String host = "localhost";
    String db = "kosan";

    Scanner sc = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Connection con;
    Statement s;
    ResultSet rs;
    private int ip;
    
    
// tambah penghuni    
public void tambahPenghuni() throws IOException, SQLException, ClassNotFoundException{        
        try {
            //Koneksi
        Class.forName("com.mysql.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
            
            String sql = "INSERT INTO t_penghuni "+" (no_kamar,nama_penghuni,telp_penghuni,profesi) "+" VALUES(?,?,?,?)";
            
            PreparedStatement pstmt = null;
            pstmt = con.prepareStatement(sql);
            
            System.out.println("**************************************");
            System.out.println("\tTambah Data Penghuni\t");
            System.out.println("**************************************");
            
            System.out.println("Harga yang tertera sesuai range kode kamar");
            System.out.println("Ekonomi\t=\tFree Wifi\t\tRp.1000000\t1 - 10");
            System.out.println("Standar\t=\tFree Wifi, Jemuran\tRp.2000000\t11 - 20");
            System.out.println("Atas\t=\tFree Wifi, AC, Kulkas\tRp.3000000\t21- 30");
            
            // input dari user  
            System.out.print("Masukan Kode Kamar = ");
            int ip = sc.nextInt();
            
            
            if (ip > 0 && ip < 31){
                this.ip = ip;
            }
            else {
                System.out.println("Kode Kamar tidak ada!");
                s.close();
                con.close();
            }
            
            pstmt.setInt(1, ip);
            
            System.out.print("Masukan Nama Penghuni Kost = ");
            String namap = br.readLine().trim();
            pstmt.setString(2, namap);
            
            System.out.print("Masukan Nomor Handphone = ");
            String nohp = sc.next().trim();
            pstmt.setString(3, nohp);
            
            System.out.print("Masukkan Profesi = ");
            String pro = br.readLine().trim();
            pstmt.setString(4, pro);
          
            int intBaris =  pstmt.executeUpdate();
            if(intBaris > 0)
         //   if (pstmt.executeUpdate() > 0) 
            {
                System.out.println("Penambahan data berhasill");
            }
            else
            {
                System.out.println("Penambahan data gagal");
            } 
             con.close();
            pstmt.close(); 
    }
          //Eror
        catch (SQLException e)
        {
          System.out.println("Koneksi Gagal" +e.toString());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("JDBC Driver tidak ditemukan");
        }
}

public void tampilPenghuni() throws ClassNotFoundException, SQLException {
       
    try {
        //Koneksi
        Class.forName("com.mysql.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
        String sql = "SELECT * FROM t_penghuni";
        
        rs = s.executeQuery(sql);
       

            System.out.println("**************************************");
            System.out.println("\tData Penghuni Kost\t");
            System.out.println("**************************************");
            System.out.println("******************************************************");
            System.out.println("*\t\t   Data Penghuni Kost\t\t     *");
            System.out.println("******************************************************");
            System.out.println("Kamar\t    Nama\t  No HP\t\t    Profesi");
            while (rs.next()) {
                
                String ip = rs.getString("no_kamar");
                String namap = rs.getString("nama_penghuni");
                String nohp = rs.getString("telp_penghuni");
                String pro = rs.getString("profesi");
                
                System.out.println(String.format("%-12s%-14s%-18s%-16s", ip, namap, nohp, pro));
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
public void ubahPenghuni() throws IOException
    {
        try{
        //Koneksi
        Class.forName("com.mysql.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        System.out.println("**************************************");
        System.out.println("\tUbah Data Penghuni\t");
        System.out.println("**************************************");
        
        // memasukkan nomor kamar yang akan diubah datanya
        System.out.print("Masukkan Nomor Kamar yang Ingin diubah = ");
        String ip_baru = sc.next();
        
        //Query sql tampil yang akan di update
        String sql = "SELECT * FROM t_penghuni WHERE no_kamar='%s'";
        sql = String.format(sql, ip_baru);
        
        //Eksekusi Query
        rs = s.executeQuery(sql);
        if (rs.next()) 
        {
        String ip   = rs.getString("no_kamar");
        String namap = rs.getString("nama_penghuni");
        String nohp   = rs.getString("telp_penghuni");
        String pro   = rs.getString("profesi");
        System.out.println("**************************************");
        System.out.println("\tData Penghuni yang akan diubah\t");
        System.out.println("**************************************");
        System.out.println("Nomor Kamar\t= " +ip);
        System.out.println("Nama Penghuni\t= " +namap);
        System.out.println("Telepon\t\t= " +nohp);
        System.out.println("Profesi\t\t= " +pro);
        
        // Validasi untuk pilihan ubah data penghuni
        System.out.print("Apakah anda yakin ingin mengubah (y/n)? ");
       String ulangi = sc.next();
        if(ulangi.equals("y")) 
        {
        
        //Memasukkan data yang diubah
        System.out.println("**************************************");
        System.out.println("\tMasukkan Data Penghuni baru\t");
        System.out.println("**************************************");
        System.out.print("Nama Penghuni  = ");
        String namap_baru = br.readLine();
        System.out.print("Nomor Handphone  = ");
        String nohp_baru = sc.next();
        
        System.out.print("Profesi  = ");
        String pro_baru = br.readLine();
        
        //Query sql update
        String sqlbaru = "UPDATE t_penghuni SET nama_penghuni ='%s', telp_penghuni ='%s', profesi='%s' WHERE no_kamar='%s'";
        sqlbaru = String.format(sqlbaru, namap_baru, nohp_baru, pro_baru, ip_baru);
        
        //Eksekusi Query Update
        s.execute(sqlbaru);
        System.out.println("Ubah Data Berhasil");
        
        if (ulangi.equalsIgnoreCase("n")) {
            System.exit(0);
        }
        }
        }
        else 
        {
            System.out.println("Data No Kamar yang diinputkan tidak ada");
        }
        
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

    // hapus data penghuni
 public void hapusPenghuni() throws IOException, ClassNotFoundException{
        
        System.out.println("**************************************");
        System.out.println("\tHapus Data Penghuni\t");
        System.out.println("**************************************");
               
        // ambil input dari user
        System.out.print("Nomor Kamar yang mau dihapus =  ");
      
        String ip = sc.next().trim();
        
        // Validasi untuk pilihan hapus data penghuni
        System.out.print("Apakah anda yakin ingin menghapus (y/n)? ");
       String ulangi = sc.next();
        if(ulangi.equals("y")) 
        {
        try 
        {
        //koneksi
        Class.forName("com.mysql.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
 
        // buat query hapus
        String sql = String.format("DELETE FROM t_penghuni WHERE no_kamar='%s'",ip);
       
        // validasi hapus data
         int intBaris =  s.executeUpdate(sql);
            if(intBaris > 0)
            {
                System.out.println("Data penghuni telah terhapus");
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
        
        if (ulangi.equalsIgnoreCase("n")) {
            System.exit(0);
        }
    }
 } 
 
        
        
 // cari penghuni berdasarkan nomor kamar
  public void cariPenghuniNo() throws IOException
    {
        
        try{
        //Koneksi
        Class.forName("com.mysql.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        
        //User memasukkan penghuni yang ingin dicari
        System.out.print("Masukkan Nomor Kamar yang dicari = ");
        String ip_cari = sc.next().trim();
        
        //Query sql tampil yang akan di update
        String sql = "SELECT * FROM t_penghuni WHERE no_kamar='%s'";
        sql = String.format(sql, ip_cari);
        
        //Eksekusi Query
        rs = s.executeQuery(sql);
        if (rs.next())
        {
            String ip   = rs.getString("no_kamar");
            String namap = rs.getString("nama_penghuni");
            String nohp  = rs.getString("telp_penghuni");
            String pro    = rs.getString("profesi");
            System.out.println("**************************************");
            System.out.println("\tData Penghuni Yang Di Cari\t");
            System.out.println("**************************************");
            System.out.println("Nomor Kamar\t= " +ip);
            System.out.println("Nama Penghuni\t= " +namap);
            System.out.println("Telepon\t\t= " +nohp);
            System.out.println("Profesi\t\t= " +pro);
        }
        else
        {
            System.out.println("Data Penghuni tidak ditemukan!");
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
 

 
 // cari data penghuni berdasarkan nama
  public void cariPenghuniNama() throws IOException
    {
        try{
        //Koneksi
        Class.forName("com.mysql.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        //User memasukkan penghuni yang ingin dicari
        System.out.print("Masukkan Nama Penghuni yang dicari = ");
        String namap_cari = br.readLine();
        
        //Query sql tampil yang akan di update
        String sql = "SELECT * FROM t_penghuni WHERE nama_penghuni='%s'";
        sql = String.format(sql, namap_cari);
        
        //Eksekusi Query
        rs = s.executeQuery(sql);
        if (rs.next())
        {
            String ip   = rs.getString("no_kamar");
            String namap = rs.getString("nama_penghuni");
            String nohp  = rs.getString("telp_penghuni");
            String pro    = rs.getString("profesi");
            System.out.println("**************************************");
            System.out.println("\tData Penghuni Yang Di Cari\t");
            System.out.println("**************************************");
            System.out.println("Nomor Kamar\t= " +ip);
            System.out.println("Nama Penghuni\t= " +namap);
            System.out.println("Telepon\t\t= " +nohp);
            System.out.println("Profesi\t\t= " +pro);
        }
        else
        {
            System.out.println("Data Penghuni tidak ditemukan!");
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
} 
