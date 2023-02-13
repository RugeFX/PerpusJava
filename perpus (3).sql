-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Feb 2023 pada 05.56
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpus`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `anggota`
--

CREATE TABLE `anggota` (
  `nik` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `namaanggota` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `kota` varchar(100) NOT NULL,
  `notelpon` varchar(20) NOT NULL,
  `tanggallahir` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `anggota`
--

INSERT INTO `anggota` (`nik`, `password`, `namaanggota`, `alamat`, `kota`, `notelpon`, `tanggallahir`) VALUES
('2000', '08f90c1a417155361a5c4b8d297e0d78', 'Rizki', 'jl.nissin', 'jakarta', '081382232323', '2023-01-01'),
('2001', 'd0fb963ff976f9c37fc81fe03c21ea7b', 'Aryaan', 'jl.masjid', 'jakarta', '081231232142', '2023-01-02'),
('2002', '4ba29b9f9e5732ed33761840f4ba6c53', 'Aryo', 'jl.potangan', 'jakarta', '081278688342', '2023-01-03'),
('2003', 'a591024321c5e2bdbd23ed35f0574dde', 'ali', 'jl.sawo', 'jakarta', '081725272922', '2023-01-04'),
('2005', 'd47268e9db2e9aa3827bba3afb7ff94a', 'naya', 'jl.sawi', 'jakarta', '081912821398', '2023-01-06'),
('2006', 'ea5a486c712a91e48443cd802642223d', 'ilyas', 'jl.apayo', 'jakarta', '081233535838', '2023-01-08'),
('2007', 'a00e5eb0973d24649a4a920fc53d9564', 'bayu', 'jl.kotatau', 'jakarta', '081237439238', '2023-01-10'),
('2008', 'ef8446f35513a8d6aa2308357a268a7e', 'jaki', 'jl.kotamati', 'jakarta', '081237645789', '2023-01-18'),
('2009', 'f1981e4bd8a0d6d8462016d2fc6276b3', 'dimas', 'jl.kotamau', 'jakarta', '081231424289', '2023-01-25'),
('2010', 'd7a84628c025d30f7b2c52c958767e76', 'semi', 'jl.kecil', 'jakarta', '081239248932', '2023-01-26'),
('2011', 'c8758b517083196f05ac29810b924aca', 'Rintami', 'Ciracas', 'Jakarta', '0812321921', '2023-01-25');

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `kodebuku` varchar(10) NOT NULL,
  `judulbuku` varchar(255) NOT NULL,
  `pengarang` varchar(255) NOT NULL,
  `tahunterbit` varchar(4) NOT NULL,
  `idkategori` varchar(6) NOT NULL,
  `idpenerbit` varchar(6) NOT NULL,
  `idgenre` varchar(6) NOT NULL,
  `urlebook` varchar(255) DEFAULT NULL,
  `urlgambar` varchar(255) DEFAULT NULL,
  `stokbuku` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`kodebuku`, `judulbuku`, `pengarang`, `tahunterbit`, `idkategori`, `idpenerbit`, `idgenre`, `urlebook`, `urlgambar`, `stokbuku`) VALUES
('BK01', 'Dellirium', 'Lauren', '2011', 'KT01', 'PN01', 'GN01', NULL, 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTSO1f806auUNhB1DHisdEfuUx7VvlcX1gJwx7OmHP3vaoTB3s5', 5),
('BK02', 'Harry Potter and the order of the phoenix', 'J.K Rowling', '2003', 'KT01', 'PN02', 'GN02', NULL, 'https://upload.wikimedia.org/wikipedia/id/3/3b/Order_of_Phoenix_cover.jpg', 4),
('BK03', 'Hujan', 'Tere Liye', '2016', 'KT01', 'PN02', 'GN02', NULL, 'https://cdn.gramedia.com/uploads/items/9786020324784_Hujan-Cover-Baru-2018.jpg', 7);

-- --------------------------------------------------------

--
-- Struktur dari tabel `genre`
--

CREATE TABLE `genre` (
  `idgenre` varchar(6) NOT NULL,
  `namagenre` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `genre`
--

INSERT INTO `genre` (`idgenre`, `namagenre`) VALUES
('GN01', 'Romantis'),
('GN02', 'Fantasi'),
('GN03', 'Komedi'),
('GN04', 'Horor'),
('GN05', 'Drama'),
('GN06', 'Sejarah'),
('GN07', 'Pendidikan'),
('GN08', 'Ilmiah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `idkategori` varchar(6) NOT NULL,
  `namakategori` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`idkategori`, `namakategori`) VALUES
('KT01', 'Novel'),
('KT02', 'Majala'),
('KT03', 'Kamus'),
('KT04', 'Komik'),
('KT05', 'Manga'),
('KT06', 'Ensikl'),
('KT07', 'Biogra'),
('KT08', 'Naskah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penerbit`
--

CREATE TABLE `penerbit` (
  `idpenerbit` varchar(6) NOT NULL,
  `namapenerbit` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `penerbit`
--

INSERT INTO `penerbit` (`idpenerbit`, `namapenerbit`) VALUES
('PN01', 'Mizan '),
('PN02', 'GPU'),
('PN03', 'BP'),
('PN04', 'PB'),
('PN05', 'MP'),
('PN06', 'Laiqa'),
('PN07', 'NBP');

-- --------------------------------------------------------

--
-- Struktur dari tabel `petugas`
--

CREATE TABLE `petugas` (
  `idpetugas` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `namapetugas` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `kota` varchar(100) NOT NULL,
  `notelpon` varchar(20) NOT NULL,
  `tanggallahir` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `petugas`
--

INSERT INTO `petugas` (`idpetugas`, `password`, `namapetugas`, `alamat`, `kota`, `notelpon`, `tanggallahir`) VALUES
('A2000', '39b233964265ab07d418a79307e4d255', 'ARYO', 'JL.NISSIN', 'JAKARTA', '0812333737898', '2023-01-02'),
('A2001', 'a362e9b32b33a8886525d756504837d0', 'ILYAS', 'JL.NISAN', 'JAKARTA', '0813787656863', '2023-01-02'),
('A2002', '7d1cfa6bc3a59fa57a1adb497286ae5a', 'ARYAAN', 'JL.NISSUN', 'JAKARTA', '0812738873893', '2023-01-03'),
('A2003', '44907bacc97a50536f657b56f027ae75', 'ALI', 'JL.NISSON', 'JAKARTA', '0817828774878', '2023-01-31'),
('A2004', '46c289e2c17af6c7e31b1102008641f0', 'AYU', 'JL.MASJID A', 'JAKARTA', '0812356221356', '2023-01-30'),
('A2006', '3e6da1900b87870dedc4d78bbfe04818', 'DIMASX', 'JL.MASJID C', 'JAKARTA', '0813258939875', '2023-01-28'),
('A2007', '4dcbbb1e9d5f485469a3fb57245cdd78', 'JAKIX', 'JL.MAJID D', 'JAKARTA', '0812498012222', '2023-01-26'),
('A2008', '1dda884d2a6e73bdcb6aa94bd59f0852', 'NAYAX', 'JL.MAJID E', 'JAKARTA', '081238398730', '2023-01-24'),
('A2009', '1ec9ea26e8b6e01fbbf0f2200e52af38', 'RIZKIX', 'JL.KOTA A', 'JAKARTA', '0812148914793', '2023-01-23'),
('A2010', 'c1b436f135ed6357fd2518c613b25a1e', 'SEMIX', 'JL.KOTA B', 'JAKARTA', '081239817249', '2023-01-23');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pinjaman`
--

CREATE TABLE `pinjaman` (
  `idpinjaman` int(11) NOT NULL,
  `idanggota` varchar(20) NOT NULL,
  `kodebuku` varchar(10) NOT NULL,
  `tanggalpinjam` date NOT NULL,
  `tanggalkembali` date NOT NULL,
  `denda` decimal(10,0) DEFAULT NULL,
  `idstatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pinjaman`
--

INSERT INTO `pinjaman` (`idpinjaman`, `idanggota`, `kodebuku`, `tanggalpinjam`, `tanggalkembali`, `denda`, `idstatus`) VALUES
(1, '2001', 'BK02', '2023-01-12', '2023-01-19', NULL, 2),
(2, '2003', 'BK03', '2023-01-07', '2023-01-14', NULL, 2),
(3, '2003', 'BK02', '2023-01-05', '2023-01-12', NULL, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `status`
--

CREATE TABLE `status` (
  `idstatus` int(11) NOT NULL,
  `namastatus` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `status`
--

INSERT INTO `status` (`idstatus`, `namastatus`) VALUES
(1, 'Dipinjam'),
(2, 'Dikembalikan'),
(3, 'Terlambat'),
(4, 'Dikembalikan Rusak'),
(5, 'Ditangguhkan');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `viewbukuterlaris`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `viewbukuterlaris` (
`kodebuku` varchar(10)
,`judulbuku` varchar(255)
,`pengarang` varchar(255)
,`tahunterbit` varchar(4)
,`idkategori` varchar(6)
,`idpenerbit` varchar(6)
,`idgenre` varchar(6)
,`urlebook` varchar(255)
,`urlgambar` varchar(255)
,`stokbuku` int(11)
,`Total` bigint(21)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `viewlaporanpinjaman`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `viewlaporanpinjaman` (
`idpinjaman` int(11)
,`namaanggota` varchar(255)
,`judulbuku` varchar(255)
,`tanggalpinjam` date
,`tanggalkembali` date
,`denda` decimal(10,0)
,`keterangaan` varchar(50)
);

-- --------------------------------------------------------

--
-- Struktur untuk view `viewbukuterlaris`
--
DROP TABLE IF EXISTS `viewbukuterlaris`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewbukuterlaris`  AS SELECT `b`.`kodebuku` AS `kodebuku`, `b`.`judulbuku` AS `judulbuku`, `b`.`pengarang` AS `pengarang`, `b`.`tahunterbit` AS `tahunterbit`, `b`.`idkategori` AS `idkategori`, `b`.`idpenerbit` AS `idpenerbit`, `b`.`idgenre` AS `idgenre`, `b`.`urlebook` AS `urlebook`, `b`.`urlgambar` AS `urlgambar`, `b`.`stokbuku` AS `stokbuku`, count(`b`.`judulbuku`) AS `Total` FROM (`buku` `b` join `pinjaman` `p`) WHERE `p`.`kodebuku` = `b`.`kodebuku` GROUP BY `b`.`judulbuku` LIMIT 0, 33  ;

-- --------------------------------------------------------

--
-- Struktur untuk view `viewlaporanpinjaman`
--
DROP TABLE IF EXISTS `viewlaporanpinjaman`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewlaporanpinjaman`  AS SELECT `p`.`idpinjaman` AS `idpinjaman`, `a`.`namaanggota` AS `namaanggota`, `b`.`judulbuku` AS `judulbuku`, `p`.`tanggalpinjam` AS `tanggalpinjam`, `p`.`tanggalkembali` AS `tanggalkembali`, `p`.`denda` AS `denda`, `s`.`namastatus` AS `keterangaan` FROM (((`buku` `b` join `status` `s`) join `anggota` `a`) join `pinjaman` `p`) WHERE `p`.`idanggota` = `a`.`nik` AND `p`.`kodebuku` = `b`.`kodebuku` AND `p`.`idstatus` = `s`.`idstatus``idstatus`  ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`nik`);

--
-- Indeks untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`kodebuku`),
  ADD KEY `Buku_fk0` (`idkategori`),
  ADD KEY `Buku_fk1` (`idpenerbit`),
  ADD KEY `Buku_fk2` (`idgenre`);

--
-- Indeks untuk tabel `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`idgenre`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`idkategori`);

--
-- Indeks untuk tabel `penerbit`
--
ALTER TABLE `penerbit`
  ADD PRIMARY KEY (`idpenerbit`);

--
-- Indeks untuk tabel `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`idpetugas`);

--
-- Indeks untuk tabel `pinjaman`
--
ALTER TABLE `pinjaman`
  ADD PRIMARY KEY (`idpinjaman`),
  ADD KEY `pinjaman_fk0` (`idanggota`),
  ADD KEY `pinjaman_fk1` (`kodebuku`),
  ADD KEY `pinjaman_fk2` (`idstatus`);

--
-- Indeks untuk tabel `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`idstatus`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pinjaman`
--
ALTER TABLE `pinjaman`
  MODIFY `idpinjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `status`
--
ALTER TABLE `status`
  MODIFY `idstatus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD CONSTRAINT `Buku_fk0` FOREIGN KEY (`idkategori`) REFERENCES `kategori` (`idkategori`),
  ADD CONSTRAINT `Buku_fk1` FOREIGN KEY (`idpenerbit`) REFERENCES `penerbit` (`idpenerbit`),
  ADD CONSTRAINT `Buku_fk2` FOREIGN KEY (`idgenre`) REFERENCES `genre` (`idgenre`);

--
-- Ketidakleluasaan untuk tabel `pinjaman`
--
ALTER TABLE `pinjaman`
  ADD CONSTRAINT `pinjaman_fk0` FOREIGN KEY (`idanggota`) REFERENCES `anggota` (`nik`),
  ADD CONSTRAINT `pinjaman_fk1` FOREIGN KEY (`kodebuku`) REFERENCES `buku` (`kodebuku`),
  ADD CONSTRAINT `pinjaman_fk2` FOREIGN KEY (`idstatus`) REFERENCES `status` (`idstatus`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
