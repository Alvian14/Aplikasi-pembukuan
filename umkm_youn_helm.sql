-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 29 Jan 2023 pada 07.13
-- Versi server: 10.4.25-MariaDB
-- Versi PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `umkm_youn_helm`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `akun`
--

CREATE TABLE `akun` (
  `id_akun` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jenis_kelamin` varchar(30) NOT NULL,
  `no_telp` varchar(13) NOT NULL,
  `alamat` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `akun`
--

INSERT INTO `akun` (`id_akun`, `nama`, `username`, `password`, `tgl_lahir`, `jenis_kelamin`, `no_telp`, `alamat`) VALUES
('A0006', 'Admin', 'Admin', '123admin', '2023-01-06', 'Laki - Laki', '081559769075', 'Nganjuk');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(10) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `stok` varchar(5) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `harga_jual` int(20) NOT NULL,
  `harga_beli` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `stok`, `kategori`, `harga_jual`, `harga_beli`) VALUES
(' B0001', ' Helm INK', '0', ' Half Face', 345000, 300000),
(' B0002', ' Helm Bogo', '18', ' Half Face', 187000, 130000),
(' B0003', ' Helm Cargloss', '21', ' Half Face', 265000, 215000),
(' B0004', ' Helm JPX', '9', ' Full Face', 630000, 555000),
(' B0005', ' Helm KYT', ' 5', ' Half Face', 545000, 500000),
(' B0006', ' Helm ADV', '4', ' Full Face', 555000, 500000),
(' B0007', ' Helm KYT K2R', '3', ' Full Face', 650000, 600000),
(' B0008', ' Helm Cakil', '3', ' Full Face', 700000, 650000),
(' B0009', ' Helm NHK', ' 4', ' Full Face', 575000, 500000),
(' B0010', 'Visor Helm', ' 5', 'Aksesoris', 115000, 95000),
(' B0011', ' Pet Helm', ' 4', ' Aksesoris', 40000, 25000),
(' B0012', ' Busa Spon Fullset', '1', ' Aksesoris', 65000, 50000),
(' B0013', ' Kacamatan Helm', ' 5', ' Aksesoris', 115000, 95000),
(' B0014', ' Spoiler', '7', ' Aksesoris', 65000, 55000),
(' B0015', ' Sarung Tangan', '6', ' Aksesoris', 30000, 25000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi_beli`
--

CREATE TABLE `detail_transaksi_beli` (
  `id_beli` varchar(10) NOT NULL,
  `tgl_beli` date NOT NULL,
  `id_supplier` varchar(10) NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `harga` int(50) NOT NULL,
  `total_harga` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `detail_transaksi_beli`
--

INSERT INTO `detail_transaksi_beli` (`id_beli`, `tgl_beli`, `id_supplier`, `id_barang`, `jumlah`, `harga`, `total_harga`) VALUES
('BT0001', '2023-01-25', 'SP001', ' B0004', 2, 630000, 1260000),
('BT0002', '2023-01-25', 'SP003', ' B0001', 1, 345000, 345000),
('BT0003', '2023-01-25', 'SP003', ' B0002', 1, 187000, 187000),
('BT0004', '2023-01-25', 'SP004', ' B0014', 1, 65000, 65000),
('BT0005', '2023-01-25', 'SP001', ' B0002', 1, 187000, 187000),
('BT0006', '2023-01-25', 'SP003', ' B0001', 1, 345000, 345000),
('BT0007', '2023-01-25', 'SP001', ' B0002', 1, 130000, 130000),
('BT0008', '2023-01-25', 'SP003', ' B0002', 1, 187000, 187000),
('BT0009', '2023-01-25', 'SP002', ' B0002', 2, 187000, 374000),
('BT0010', '2023-01-25', 'SP003', ' B0002', 1, 187000, 187000),
('BT0011', '2023-01-25', 'SP003', ' B0004', 2, 630000, 1260000),
('BT0012', '2023-01-25', 'SP003', ' B0004', 5, 630000, 3150000);

--
-- Trigger `detail_transaksi_beli`
--
DELIMITER $$
CREATE TRIGGER `tambah_stok` AFTER INSERT ON `detail_transaksi_beli` FOR EACH ROW BEGIN
UPDATE barang
SET stok = stok + NEW.jumlah
WHERE id_barang = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi_jual`
--

CREATE TABLE `detail_transaksi_jual` (
  `id_jual` varchar(10) NOT NULL,
  `tgl_jual` date NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `harga_jual` int(50) NOT NULL,
  `total_harga` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `detail_transaksi_jual`
--

INSERT INTO `detail_transaksi_jual` (`id_jual`, `tgl_jual`, `id_barang`, `jumlah`, `harga_jual`, `total_harga`) VALUES
('T0001', '2023-01-03', ' B0003', 2, 265000, 530000),
('T0002', '2023-01-03', ' B0001', 2, 345000, 690000),
('T0003', '2023-01-03', ' B0001', 2, 345000, 690000),
('T0003', '2023-01-03', ' B0001', 1, 345000, 345000),
('T0004', '2023-01-03', ' B0003', 1, 265000, 265000),
('T0005', '2023-01-03', ' B0008', 1, 700000, 700000),
('T0006', '2023-01-03', ' B0002', 1, 187000, 187000),
('T0007', '2023-01-03', ' B0001', 1, 345000, 345000),
('T0008', '2023-01-03', ' B0001', 4, 345000, 1380000),
('T0009', '2023-01-23', ' B0003', 2, 265000, 530000),
('T0010', '2023-01-24', ' B0001', 1, 345000, 345000),
('T0011', '2023-01-24', ' B0001', 1, 345000, 345000),
('T0012', '2023-01-24', ' B0002', 1, 187000, 187000),
('T0013', '2023-01-24', ' B0001', 2, 345000, 690000),
('T0014', '2023-01-24', ' B0002', 1, 187000, 187000),
('T0015', '2023-01-24', ' B0003', 1, 265000, 265000),
('T0016', '2023-01-24', ' B0001', 2, 345000, 690000),
('T0017', '2023-01-25', ' B0001', 1, 345000, 345000),
('T0018', '2023-01-25', ' B0003', 1, 265000, 265000),
('T0019', '2023-01-25', ' B0004', 1, 630000, 630000),
('T0020', '2023-01-25', ' B0002', 2, 187000, 374000),
('T0021', '2023-01-25', ' B0003', 1, 265000, 265000),
('T0021', '2023-01-25', ' B0004', 2, 630000, 1260000),
('T0022', '2023-01-26', ' B0001', 3, 345000, 1035000);

--
-- Trigger `detail_transaksi_jual`
--
DELIMITER $$
CREATE TRIGGER `kurang_stok` AFTER INSERT ON `detail_transaksi_jual` FOR EACH ROW BEGIN
UPDATE barang
SET stok = stok - NEW.jumlah
WHERE id_barang = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` varchar(10) NOT NULL,
  `nama_supplier` varchar(50) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`, `no_hp`, `alamat`) VALUES
('SP001', 'Dwijaya Star Indonesia', '08113112388', 'Sidoarjo'),
('SP002', 'UD Rido Helm ', '081289616311', 'Jakarta'),
('SP003', 'Helm Jaya Abadi', '085822334455', 'Surabaya'),
('SP004', 'PT Gardatex Trimitra Sejahtera', '081310244925', 'Tangerang');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_beli`
--

CREATE TABLE `transaksi_beli` (
  `id_beli` varchar(10) NOT NULL,
  `tgl_beli` date NOT NULL,
  `total_harga` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi_beli`
--

INSERT INTO `transaksi_beli` (`id_beli`, `tgl_beli`, `total_harga`) VALUES
('BT0001', '2023-01-25', 1260000),
('BT0002', '2023-01-25', 345000),
('BT0003', '2023-01-25', 187000),
('BT0004', '2023-01-25', 65000),
('BT0005', '2023-01-25', 187000),
('BT0006', '2023-01-25', 345000),
('BT0007', '2023-01-25', 130000),
('BT0008', '2023-01-25', 187000),
('BT0009', '2023-01-25', 374000),
('BT0010', '2023-01-25', 187000),
('BT0011', '2023-01-25', 1260000),
('BT0012', '2023-01-25', 3150000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_jual`
--

CREATE TABLE `transaksi_jual` (
  `id_jual` varchar(10) NOT NULL,
  `tgl_jual` date NOT NULL,
  `total_harga` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi_jual`
--

INSERT INTO `transaksi_jual` (`id_jual`, `tgl_jual`, `total_harga`) VALUES
('T0001', '2023-01-03', 530000),
('T0002', '2023-01-03', 690000),
('T0003', '2023-01-03', 1035000),
('T0004', '2023-01-03', 265000),
('T0005', '2023-01-03', 700000),
('T0006', '2023-01-03', 187000),
('T0007', '2023-01-03', 345000),
('T0008', '2023-01-03', 1380000),
('T0009', '2023-01-23', 530000),
('T0010', '2023-01-24', 345000),
('T0011', '2023-01-24', 345000),
('T0012', '2023-01-24', 187000),
('T0013', '2023-01-24', 690000),
('T0014', '2023-01-24', 187000),
('T0015', '2023-01-24', 265000),
('T0016', '2023-01-24', 690000),
('T0017', '2023-01-25', 345000),
('T0018', '2023-01-25', 265000),
('T0019', '2023-01-25', 630000),
('T0020', '2023-01-25', 374000),
('T0021', '2023-01-25', 1525000),
('T0022', '2023-01-26', 1035000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `detail_transaksi_beli`
--
ALTER TABLE `detail_transaksi_beli`
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_beli` (`id_beli`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indeks untuk tabel `detail_transaksi_jual`
--
ALTER TABLE `detail_transaksi_jual`
  ADD KEY `id_jual` (`id_jual`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indeks untuk tabel `transaksi_beli`
--
ALTER TABLE `transaksi_beli`
  ADD PRIMARY KEY (`id_beli`);

--
-- Indeks untuk tabel `transaksi_jual`
--
ALTER TABLE `transaksi_jual`
  ADD PRIMARY KEY (`id_jual`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_transaksi_beli`
--
ALTER TABLE `detail_transaksi_beli`
  ADD CONSTRAINT `detail_transaksi_beli_ibfk_1` FOREIGN KEY (`id_beli`) REFERENCES `transaksi_beli` (`id_beli`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_beli_ibfk_3` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_beli_ibfk_4` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_transaksi_jual`
--
ALTER TABLE `detail_transaksi_jual`
  ADD CONSTRAINT `detail_transaksi_jual_ibfk_1` FOREIGN KEY (`id_jual`) REFERENCES `transaksi_jual` (`id_jual`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_jual_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
