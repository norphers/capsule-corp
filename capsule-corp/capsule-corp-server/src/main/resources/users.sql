-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-09-2020 a las 16:32:06
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `capsulecorpdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`dtype`, `id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES
('Employee', 1, 'dr.brief@capsulecorp.com', 'Brief', '', 'bulmathebest1', 1),
('Employee', 2, 'dra.bulma@capsulecorp.com', 'Bulma', '', 'bulmathebest2', 1),
('Employee', 3, 'trunks@capsulecorp.com', 'Trunks', '', 'killfreezer1', 1),
('Employee', 4, 'admin@capsulecorp.com', 'admin', '', 'toor', 0),
('Employee', 5, 'j.doe@demo.com', 'john', 'doe', '1234', 3),
('Employee', 6, 'm.smith@demo.com', 'maria', 'smith', '1234', 3),
('Employee', 7, 'j.walters@demo.com', 'jess', 'walters', '1234', 3),
('Employee', 8, 'b.howland@company1.com', 'Brian', 'Howland', '1234', 2),
('Employee', 9, 'a.watford@company3.com', 'Ana', 'Watford', '1234', 2),
('Employee', 10, 'm.mckinsey@company4.com', 'Mary', 'McKinsey', '1234', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
