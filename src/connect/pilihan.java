/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class pilihan {
    // created object
    penghuni p = new penghuni();    
    
    public void menu() throws IOException, SQLException, ClassNotFoundException {
        // create object
        Scanner sc = new Scanner(System.in);
        
        String ulangi = "y";
        while(ulangi.equals("y")) {
        
        System.out.println(" ");
        System.out.println("======================================");
        System.out.println("             MENU UTAMA               ");
        System.out.println("======================================");
        System.out.println("1. Sewa Kost");
        System.out.println("2. Transaksi Kost");
        System.out.println("3. Data Penghuni");
        System.out.println("0. Keluar");
        System.out.println("=====================================");
        System.out.print("Pilihan = ");
        int pilihan = sc.nextInt();
        System.out.println("=====================================");

        switch (pilihan) {
               case 0:{
                System.exit(0);
            }
            case 1:{

                System.out.println("======================================");
                System.out.println("              Penghuni Kost               ");
                System.out.println("======================================");
                System.out.println("1. Tambah Data Penghuni");
                System.out.println("2. Tampil Data Penghuni ");
                System.out.println("3. Ubah Data Penghuni");
                System.out.println("4. Cari Data Penghuni");
                System.out.println("5. Hapus Data Penghuni");
                System.out.println("0. Keluar");
                System.out.println("=====================================");
                System.out.print("Pilihan = ");
                int pilihan1 = sc.nextInt();
                System.out.println("=====================================");
                switch (pilihan1) {
                case 0:{
                    System.exit(0);
                break;
                    }
                case 1:{
                    p.tambahPenghuni();
                break;
                }
                case 2:{
                    p.tampilPenghuni();
                break;
                    }
                case 3:{
                    p.ubahPenghuni();
                break;
                    }
                case 4:{
                    System.out.println("======================================");
                System.out.println("           Cari Data Penghuni Kost              ");
                System.out.println("======================================");
                System.out.println("1. Cari Penghuni berdasarkan kode kamar");
                System.out.println("2. Cari Penghuni berdasarkan nama");
                System.out.println("0. Keluar");
                System.out.println("=====================================");
                System.out.print("Pilihan = ");
                int cari = sc.nextInt();
                System.out.println("=====================================");
                    switch (cari) {
                   case 0:{
                       System.exit(0);
                   break;
                       }
                   case 1:{
                       p.cariPenghuniNo();
                   break;
                   }
                   case 2:{
                       p.cariPenghuniNama();
                       break;
                   }
                case 5:
                {
                    p.hapusPenghuni();
                    break;
                }
                
                /*
                case 0:{
                    System.exit(0);
                break;
                    }
                  }
                }
                    break;
        }
        }
  /*          case 2:{
                System.out.println("======================================");
                System.out.println("             Data Barang              ");
                System.out.println("======================================");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Update Barang");
                System.out.println("3. Tampil Barang");
                System.out.println("4. Cari Barang");
                System.out.println("5. Hapus Barang");
                System.out.println("0. Keluar");
                System.out.println("=====================================");
                System.out.print("Pilihan = ");
                int pilih2 = input.nextInt();
                System.out.println("=====================================");
                switch (pilih2) {
                case 1:{
                    brg.tambah();
                break;
                    }
                case 2:{
                    brg.update();
                break;
                    }
                case 3:{
                    brg.tampil();
                break;
                    }
                case 4:{
                    brg.cari();
                break;
                    }
                case 5:{
                    brg.hapus();
                break;
                    }
                case 0:{
                    System.exit(0);
                break;
                    }
                  }
                }
                    break;
            case 3:{
                System.out.println("======================================");
                System.out.println("             Data Keuangan            ");
                System.out.println("======================================");
                System.out.println("1. Tampil Data Pemasukan");
                System.out.println("2. Cari Data Pemasukan Berdasarkan Tggl");
                System.out.println("3. Tambah Pengeluaran");
                System.out.println("4. Tampil Pengeluaran");
                System.out.println("5. Hapus Pengeluaran");
                System.out.println("0. Keluar");
                System.out.println("=====================================");
                System.out.print("Pilihan = ");
                int pilih3 = input.nextInt();
                System.out.println("=====================================");
                switch (pilih3) {
                case 1:{
                    ku.tampil_pemasukan();
                break;
                    }
                case 2:{
                    ku.tampil_tanggal();
                break;
                    }
                case 3:{
                    ku.tambah_pengeluarah();
                break;
                    }
                case 4:{
                    ku.tampil_pengeluaran();
                break;
                    }
                case 5:{
                    ku.hapus_pengeluaran();
                break;
                    }
                case 0:{
                    System.exit(0);
                break;
                    }
                  }
                }
                    break;
            case 0:{
                System.exit(0);
            }
                break;
        } */}}}}        
        }
        System.out.println("=====================================");
        System.out.print("Apakah anda ingin mengulang (y/n)? ");
        ulangi = sc.next();
        if(ulangi.equalsIgnoreCase("n")) {
            System.exit(0);
        }
    }
    }
}
        