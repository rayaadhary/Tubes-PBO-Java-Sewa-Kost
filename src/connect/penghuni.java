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
    String pwd  = "";
    String host = "localhost";
    String db   = "kosan";
    Scanner sc = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Connection con;
    Statement s;
    ResultSet rs;
    
    
// tambah penghuni    
public void tambahPenghuni() throws IOException, SQLException, ClassNotFoundException{        
        try {
            //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
            
            String sql = "INSERT INTO t_penghuni "+" (no_kamar,nama_penghuni,telp_penghuni,profesi) "+" VALUES(?,?,?,?)";
            
            PreparedStatement pstmt = null;
            pstmt = con.prepareStatement(sql);
            
            System.out.println("Harga yang tertera sesuai range kode kamar");
            System.out.println("Ekonomi  = \"Rp.1000000\" , 1   - 10");
            System.out.println("Standar   = \"Rp.2000000\",  11  - 20");
            System.out.println("Atas        = \"Rp.3000000\",   21  - 30");
              // input dari user
            System.out.print("Masukan Kode Kamar  : ");
            String ip = sc.next().trim();
            pstmt.setString(1, ip);
            
            System.out.print("Masukan Nama Penghuni Kost : ");
            String namap = br.readLine().trim();
            pstmt.setString(2, namap);
            
            System.out.print("Masukan Nomor Handphone : ");
            String nohp = sc.next().trim();
            pstmt.setString(3, nohp);
            
            System.out.print("Masukkan Profesi : ");
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
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
        String sql = "SELECT * FROM t_penghuni";
        
        rs = s.executeQuery(sql);
       
         
            System.out.println("+----------------------------------------------------+");
            System.out.println("|\t\tDATA PENGHUNI KOSTAN\t\t     |");
            System.out.println("+----------------------------------------------------+");

            while (rs.next()) {
                
                String ip = rs.getString("no_kamar");
                String namap = rs.getString("nama_penghuni");
                String nohp = rs.getString("telp_penghuni");
                String pro = rs.getString("profesi");
                
                
                System.out.println(String.format("%-6s%-20s%-14s%-14s", ip, namap, nohp, pro));
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
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        // memasukkan nomor kamar yang akan diubah datanya
        System.out.print("Masukkan Nomor Kamar yang Ingin diubah = ");
        String ip_baru = sc.next();
        
        //Query sql tampil yang akan di update
        String sql = "SELECT * FROM t_penghuni WHERE no_kamar='%s'";
        sql = String.format(sql, ip_baru);
        
        //Eksekusi Query
        rs = s.executeQuery(sql);
        rs.next();
        String ip   = rs.getString("no_kamar");
        String namap = rs.getString("nama_penghuni");
        String nohp   = rs.getString("telp_penghuni");
        String pro   = rs.getString("profesi");
        System.out.println("+----------------------------------------------------+");
        System.out.println("       Data Penghuni yang akan diubah     ");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Nomor Kamar\t\t  = " +ip);
        System.out.println("Nama Penghuni\t\t  = " +namap);
        System.out.println("Nomor Handphone\t\t = " +nohp);
        System.out.println("Profesi\t\t  = " +pro);
        
        
        //Memasukkan data yang diubah
        System.out.println("+----------------------------------------------------+");
        System.out.println("         Masukkan Data Penghuni baru         ");
        System.out.println("+----------------------------------------------------+");
        System.out.print("Nama Penghuni  = ");
        String namap_baru = sc.next();
        System.out.print("Nomor Handphone  = ");
        String nohp_baru = sc.next();
        
        System.out.print("Profesi  = ");
        String pro_baru = sc.next();
        
        //Query sql update
        String sqlbaru = "UPDATE t_penghuni SET nama_penghuni ='%s', telp_penghuni ='%s', profesi='%s' WHERE no_kamar='%s'";
        sqlbaru = String.format(sqlbaru, namap_baru, nohp_baru, pro_baru, ip_baru);
        
        //Eksekusi Query Update
        s.execute(sqlbaru);
        System.out.println("Ubah Data Berhasil");
        
        //Close Statement dan Conn
        con.close();
        s.close();
        
        //Eror
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

    // hapus data penghuni
 public void hapusPenghuni() throws IOException, ClassNotFoundException{
    
        try {
        //koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
            
            
        // ambil input dari user
        System.out.print("Nomor Kamar yang mau dihapus =  ");
      
        String ip = sc.next().trim();
        
        // buat query hapus
        String sql = String.format("DELETE FROM t_penghuni WHERE no_kamar='%s'",ip);
       
        // hapus data
        s.execute(sql);
        
        System.out.println("Data telah terhapus...");
        
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
 
 // cari penghuni berdasarkan nomor kamar
  public void cariPenghuniNo() throws IOException
    {
        
        try{
        //Koneksi
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
        con = DriverManager.getConnection(urlValue);
        s = con.createStatement();
        
        //User memasukkan penghuni yang ingin dicari
        System.out.print("Masukkan Nomor Kamar yang dicari = ");
        String ip_cari = br.readLine();
        
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
            System.out.println("====================================");
            System.out.println("       Data Penghuni Yang Di Cari     ");
            System.out.println("====================================");
            System.out.println("Nomor Kamar = " +ip);
            System.out.println("Nama Penghuni  = " +namap);
            System.out.println("Telepon = " +nohp);
            System.out.println("Profesi = " +pro);
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
        Class.forName("com.mysql.cj.jdbc.Driver");
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
            System.out.println("====================================");
            System.out.println("       Data Penghuni Yang Di Cari     ");
            System.out.println("====================================");
            System.out.println("Nomor Kamar = " +ip);
            System.out.println("Nama Penghuni  = " +namap);
            System.out.println("Telepon = " +nohp);
            System.out.println("Profesi = " +pro);
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