-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2019 at 11:52 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bmi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `contact` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `username`, `password`, `contact`) VALUES
(1, 'Admin', 'admin', '1234', '017777777');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `serial` int(11) NOT NULL,
  `serial_FK` int(11) NOT NULL,
  `BMI` double NOT NULL,
  `insertDate` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`serial`, `serial_FK`, `BMI`, `insertDate`) VALUES
(1, 1, 100, '0'),
(2, 1, 1550, '2019-03-20'),
(3, 1, 10.9, '2019-03-20'),
(4, 2, 516.7, '2019-03-20'),
(5, 1, 0.4, '2019-03-20'),
(6, 4, 31000.1, '2019-03-20'),
(7, 1, 77.5, '2019-03-20'),
(8, 5, 31, '2019-03-20');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `serial` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(35) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `address` varchar(150) NOT NULL,
  `dob` date NOT NULL,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`serial`, `name`, `email`, `username`, `password`, `contact`, `address`, `dob`, `height`, `weight`, `gender`) VALUES
(1, '1', '1', '1', '1', '1', '1', '2010-03-19', 100, 70, 'Male'),
(2, '3', '3', '3', '3', '3', '3', '2013-03-20', 3, 3, 'Female'),
(3, '4', '4', '4', '4', '4', '4', '2019-03-05', 4, 4, 'Male'),
(4, '5', '5', '5', '5', '5', '5', '1900-03-01', 5, 5, 'Female'),
(5, '6', '6', '6', '6', '6', '6', '2000-03-20', 6, 6, 'Female'),
(6, '5', '5', '5', '5', '5', '5', '2019-03-22', 5, 5, 'Male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`serial`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`serial`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `serial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `serial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
