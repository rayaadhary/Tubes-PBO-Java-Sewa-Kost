/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.22-MariaDB : Database - kosan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kosan` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `kosan`;

/*Table structure for table `t_kamar` */

DROP TABLE IF EXISTS `t_kamar`;

CREATE TABLE `t_kamar` (
  `jenis_kamar` varchar(10) NOT NULL,
  `jumlah_kamar` int(2) DEFAULT NULL,
  `ket_no_kamar` varchar(8) DEFAULT NULL,
  `fasilitas` varchar(40) DEFAULT NULL,
  `harga_bulan` int(8) DEFAULT NULL,
  PRIMARY KEY (`jenis_kamar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_kamar` */

insert  into `t_kamar`(`jenis_kamar`,`jumlah_kamar`,`ket_no_kamar`,`fasilitas`,`harga_bulan`) values 
('Atas',10,'21-30','Free wifi, AC, Kulkas',3000000),
('Ekonomi',10,'1-10','Free wifi',1000000),
('Standar',10,'11-20','Free wifi, jemuran',2000000);

/*Table structure for table `t_penghuni` */

DROP TABLE IF EXISTS `t_penghuni`;

CREATE TABLE `t_penghuni` (
  `no_kamar` int(2) NOT NULL,
  `nama_penghuni` varchar(20) DEFAULT NULL,
  `telp_penghuni` varchar(13) DEFAULT NULL,
  `profesi` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`no_kamar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_penghuni` */

insert  into `t_penghuni`(`no_kamar`,`nama_penghuni`,`telp_penghuni`,`profesi`) values 
(22,'Yaeger','089525670899','Mahasiswa');

/*Table structure for table `t_transaksi` */

DROP TABLE IF EXISTS `t_transaksi`;

CREATE TABLE `t_transaksi` (
  `id_transaksi` int(5) NOT NULL AUTO_INCREMENT,
  `no_kamar` int(2) DEFAULT NULL,
  `jenis_kamar` varchar(10) DEFAULT NULL,
  `nama_penghuni` varchar(20) DEFAULT NULL,
  `tanggal_transaksi` date DEFAULT NULL,
  `lama_sewa` int(2) DEFAULT NULL,
  `harga_bulan` int(8) DEFAULT NULL,
  `total` int(12) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `no_kamar` (`no_kamar`),
  KEY `t_transaksi_ibfk_3` (`jenis_kamar`),
  CONSTRAINT `t_transaksi_ibfk_2` FOREIGN KEY (`no_kamar`) REFERENCES `t_penghuni` (`no_kamar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_transaksi_ibfk_3` FOREIGN KEY (`jenis_kamar`) REFERENCES `t_kamar` (`jenis_kamar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_transaksi` */

insert  into `t_transaksi`(`id_transaksi`,`no_kamar`,`jenis_kamar`,`nama_penghuni`,`tanggal_transaksi`,`lama_sewa`,`harga_bulan`,`total`) values 
(12,22,'Atas','Yaeger','2022-01-17',2,3000000,6000000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
