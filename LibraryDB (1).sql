-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 04, 2024 at 01:28 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `LibraryDB`
--
CREATE DATABASE IF NOT EXISTS `LibraryDB` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `LibraryDB`;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `publication_year` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `book`:
--

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `author`, `isbn`, `publication_year`, `title`) VALUES
(2, 'John Doe', '1234567890', 0, 'Sample Book Title');

-- --------------------------------------------------------

--
-- Table structure for table `borrowing_record`
--

DROP TABLE IF EXISTS `borrowing_record`;
CREATE TABLE `borrowing_record` (
  `id` bigint(20) NOT NULL,
  `borrow_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `patron_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `borrowing_record`:
--   `patron_id`
--       `patron` -> `id`
--   `book_id`
--       `book` -> `id`
--

--
-- Dumping data for table `borrowing_record`
--

INSERT INTO `borrowing_record` (`id`, `borrow_date`, `return_date`, `book_id`, `patron_id`) VALUES
(1, '2024-07-04', '2024-07-04', 2, 2),
(2, '2024-07-04', NULL, 2, 3),
(3, '2024-07-04', NULL, 2, 3),
(4, '2024-07-04', NULL, 2, 4),
(5, '2024-07-04', NULL, 2, 4),
(6, '2024-07-04', NULL, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `patron`
--

DROP TABLE IF EXISTS `patron`;
CREATE TABLE `patron` (
  `id` bigint(20) NOT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `contact_info` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `patron`:
--

--
-- Dumping data for table `patron`
--

INSERT INTO `patron` (`id`, `contact`, `name`, `contact_info`) VALUES
(1, NULL, 'Sample Book Title', NULL),
(2, '1111111', 'Sample Title', NULL),
(3, NULL, 'Sample Book Title', NULL),
(4, 'J56789', 'Sample Book Title', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `borrowing_record`
--
ALTER TABLE `borrowing_record`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK79d6bb8ptx41act3qbt5pxuwm` (`book_id`),
  ADD KEY `FK6e5fdl33e4cvv2jgacf9bpswj` (`patron_id`);

--
-- Indexes for table `patron`
--
ALTER TABLE `patron`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `borrowing_record`
--
ALTER TABLE `borrowing_record`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `patron`
--
ALTER TABLE `patron`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrowing_record`
--
ALTER TABLE `borrowing_record`
  ADD CONSTRAINT `FK6e5fdl33e4cvv2jgacf9bpswj` FOREIGN KEY (`patron_id`) REFERENCES `patron` (`id`),
  ADD CONSTRAINT `FK79d6bb8ptx41act3qbt5pxuwm` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
