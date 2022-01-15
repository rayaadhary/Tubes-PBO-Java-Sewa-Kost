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
    transaksi t = new transaksi();
    kamar k = new kamar();
    
    public void menu() throws IOException, SQLException, ClassNotFoundException {
        // create object
        Scanner sc = new Scanner(System.in);
        
        String ulangi = "y";
        while(ulangi.equals("y")) {
        
        System.out.println(" ");
        System.out.println("Kamar Kost menyediakan Kost, selain itu Apalagi!");
        System.out.println("Harga Mulai 1 Jutaan!/bulan");
        System.out.println("*****************************");
        System.out.println("\tMENU UTAMA\t");
        System.out.println("*****************************");
        System.out.println("1. Sewa Kost");
        System.out.println("2. Transaksi Kost");
        System.out.println("3. Keterangan Kost");
        System.out.println("0. Keluar");
        System.out.println("*****************************");
        System.out.print("Pilihan = ");
        int pilihan = sc.nextInt();
        System.out.println("*****************************");

        switch (pilihan) {
               case 0:{
                System.exit(0);
            }
            case 1:{

                System.out.println("*************************************");
                System.out.println("\tPenghuni Kost\t");
                System.out.println("*************************************");
                System.out.println("1. Tambah Data Penghuni");
                System.out.println("2. Tampil Data Penghuni ");
                System.out.println("3. Ubah Data Penghuni");
                System.out.println("4. Cari Data Penghuni");
                System.out.println("5. Hapus Data Penghuni");
                System.out.println("0. Keluar");
                System.out.println("*************************************");
                System.out.print("Pilihan = ");
                int pilihan1 = sc.nextInt();
                System.out.println("*************************************");
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
                System.out.println("*********************************************************");
                System.out.println("\tCari Data Penghuni Kost\t");
                System.out.println("*********************************************************");
                System.out.println("1. Cari Penghuni berdasarkan kode kamar");
                System.out.println("2. Cari Penghuni berdasarkan nama");
                System.out.println("0. Keluar");
                System.out.println("*********************************************************");
                System.out.print("Pilihan = ");
                int cari1 = sc.nextInt();
                System.out.println("*********************************************************");
                    switch (cari1) {
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
                }
                    break;
                }
                case 5:
                {
                    p.hapusPenghuni();
                    break;
                }
                    }
                    break;
                }
                
            case 2:{
                System.out.println("****************************************");
                System.out.println("\tTransaksi Kost\t");
                System.out.println("****************************************");
                System.out.println("1. Transaksi Sewa Kost ");
                System.out.println("2. Tampil Data Transaksi");
                System.out.println("3. Cari Transaksi Kost");
                System.out.println("4. Ubah Transaksi Kost");
                System.out.println("5. Hapus Transaksi Kost");
                System.out.println("0. Keluar");
                System.out.println("****************************************");
                System.out.print("Pilihan = ");
                int pilihan2 = sc.nextInt();
                System.out.println("****************************************");
                switch (pilihan2) {
                   case 0:{
                    System.exit(0);
                break;
                    }    
                case 1:{
                    t.transaksi_kost();
                break;
                    }
                case 2:{
                    t.tampilTransaksi();
                break;
                    } 
                case 3:{
                System.out.println("**********************************************************");
                System.out.println("\t\tCari Data Transaksi Kost\t\t");
                System.out.println("**********************************************************");
                System.out.println("1. Cari Transaksi berdasarkan kode kamar");
                System.out.println("2. Cari Transaksi berdasarkan nama");
                System.out.println("0. Keluar");
                System.out.println("**********************************************************");
                System.out.print("Pilihan = ");
                int cari2 = sc.nextInt();
                System.out.println("**********************************************************");
                    switch (cari2) {
                   case 0:{
                       System.exit(0);
                   break;
                       }
                   case 1:{
                       t.cariTransaksiNo();
                   break;
                   }
                   case 2:{
                       t.cariTransaksiNama();
                       break;
                   }
                }
                    break;
                  }
                case 4:{
                    t.ubahTransaksi();
                    break;
                }
                case 5:{
                    t.hapusTransaksi();
                    break;
                }
                }
                break;
            }
            case 3:{
                k.ket_kost();
                break;
            }
       /*     case 3:{
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
*/

    }
        System.out.println("***************************************");
        System.out.print("Apakah anda ingin mengulang (y/n)? ");
        ulangi = sc.next();
        if(ulangi.equalsIgnoreCase("n")) {
            System.exit(0);
        }
    }
}
}
        