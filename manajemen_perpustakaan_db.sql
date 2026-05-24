-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 24 Bulan Mei 2026 pada 15.41
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `manajemen_perpustakaan_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `book`
--

CREATE TABLE `book` (
  `id_book` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `author` varchar(150) NOT NULL,
  `year` year(4) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT 0,
  `id_rack` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `book`
--

INSERT INTO `book` (`id_book`, `title`, `author`, `year`, `stock`, `id_rack`) VALUES
(2, 'Revolusi Prancis', 'Lion SKanady', '2015', 50, 1),
(3, 'Cara Membuat Orang Sabar Marah', 'Sapri', '2026', 100, 3),
(4, 'Logika Logila', 'Apri', '2025', 37, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee`
--

CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `employee`
--

INSERT INTO `employee` (`id_employee`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin123', 'manager');

-- --------------------------------------------------------

--
-- Struktur dari tabel `loan`
--

CREATE TABLE `loan` (
  `id_loan` int(11) NOT NULL,
  `id_member` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `tanggal_pinjam` date NOT NULL,
  `tanggal_kembali` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `loan`
--

INSERT INTO `loan` (`id_loan`, `id_member`, `id_book`, `tanggal_pinjam`, `tanggal_kembali`) VALUES
(1, 3, 3, '2026-05-24', '2026-05-24');

-- --------------------------------------------------------

--
-- Struktur dari tabel `member`
--

CREATE TABLE `member` (
  `id_member` int(11) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '',
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` enum('aktif','non-aktif') NOT NULL DEFAULT 'aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `member`
--

INSERT INTO `member` (`id_member`, `name`, `email`, `password`, `status`) VALUES
(2, 'Prabobo', 'Prapeler@gmail.com', 'bijisatu', 'aktif'),
(3, 'titit hitam', 'tisu@gmail.com', 'tisu', 'aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `rack`
--

CREATE TABLE `rack` (
  `id_rack` int(11) NOT NULL,
  `rack_name` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `rack`
--

INSERT INTO `rack` (`id_rack`, `rack_name`, `category`, `location`) VALUES
(1, '1A', 'Horror', 'Section A'),
(3, '2A', 'Motivasi', 'Section A');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id_book`),
  ADD KEY `fk_book_rack` (`id_rack`);

--
-- Indeks untuk tabel `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id_employee`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indeks untuk tabel `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id_loan`),
  ADD KEY `fk_loan_member` (`id_member`),
  ADD KEY `fk_loan_book` (`id_book`);

--
-- Indeks untuk tabel `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id_member`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indeks untuk tabel `rack`
--
ALTER TABLE `rack`
  ADD PRIMARY KEY (`id_rack`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `book`
--
ALTER TABLE `book`
  MODIFY `id_book` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `employee`
--
ALTER TABLE `employee`
  MODIFY `id_employee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `loan`
--
ALTER TABLE `loan`
  MODIFY `id_loan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `member`
--
ALTER TABLE `member`
  MODIFY `id_member` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `rack`
--
ALTER TABLE `rack`
  MODIFY `id_rack` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `fk_book_rack` FOREIGN KEY (`id_rack`) REFERENCES `rack` (`id_rack`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `fk_loan_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_loan_member` FOREIGN KEY (`id_member`) REFERENCES `member` (`id_member`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
