-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 19 juin 2019 à 13:15
-- Version du serveur :  10.3.15-MariaDB
-- Version de PHP :  7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `parser`
--

-- --------------------------------------------------------

--
-- Structure de la table `logline`
--

CREATE TABLE `logline` (
  `ID` bigint(20) NOT NULL,
  `DATELOG` date DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `REQUEST` varchar(255) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `USERAGENT` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `loglinesynthese`
--

CREATE TABLE `loglinesynthese` (
  `ID` bigint(20) NOT NULL,
  `DATELOGLINESYNTHES` datetime DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `SUMPERHOUR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `logsynthese`
--

CREATE TABLE `logsynthese` (
  `ID` bigint(20) NOT NULL,
  `DURATION` int(11) DEFAULT NULL,
  `HRESHOLD` int(11) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `STARTDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `logline`
--
ALTER TABLE `logline`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `loglinesynthese`
--
ALTER TABLE `loglinesynthese`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `logsynthese`
--
ALTER TABLE `logsynthese`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
