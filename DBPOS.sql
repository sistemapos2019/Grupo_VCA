-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 192.168.43.60:3306
-- Tiempo de generación: 26-06-2019 a las 20:19:54
-- Versión del servidor: 5.5.62
-- Versión de PHP: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `DBPOS`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(1, 'BEBIDAS CALIENTES'),
(2, 'BEBIDAS FRIAS'),
(3, 'POSTRES'),
(4, 'PRINCIPAL'),
(5, 'pupusas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_orden`
--

CREATE TABLE `detalle_orden` (
  `id` int(11) NOT NULL,
  `producto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalle_orden`
--

INSERT INTO `detalle_orden` (`id`, `producto`, `cantidad`, `precio`) VALUES
(22, 6, 1, 1),
(22, 11, 2, 0.7),
(22, 13, 1, 0.5),
(23, 6, 1, 1),
(23, 14, 3, 1.0499999999999998);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden`
--

CREATE TABLE `orden` (
  `id` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `mesero` varchar(100) DEFAULT NULL,
  `mesa` int(2) DEFAULT NULL,
  `cliente` varchar(100) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `observaciones` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `orden`
--

INSERT INTO `orden` (`id`, `fecha`, `mesero`, `mesa`, `cliente`, `estado`, `total`, `observaciones`) VALUES
(22, '2019-06-26', 'Nayics', 1, 'bukake', 1, 2.2, 'queso con loroco'),
(23, '2019-06-26', 'Juan Carlos Villagrand', 3, 'kiko', 0, 2.05, 'es kiko del chavo del 8');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametros`
--

CREATE TABLE `parametros` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(100) DEFAULT NULL,
  `valor` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `parametros`
--

INSERT INTO `parametros` (`id`, `nombre`, `valor`) VALUES
(1, 'ModoEntorno', NULL),
(2, 'Nombre', NULL),
(3, 'Descripcion', NULL),
(4, 'Telefono', NULL),
(5, 'Nit', NULL),
(6, 'Giro', NULL),
(7, 'Direccion', NULL),
(8, 'ProductoPreparado', NULL),
(9, 'ProductoNoPreparado', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `categoria` int(11) DEFAULT NULL,
  `preparado` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`, `categoria`, `preparado`) VALUES
(1, 'PEPSI', 0.5, 2, NULL),
(2, 'COCA COLA', 0.6, 2, NULL),
(3, 'SUPREMA', 1.5, 2, NULL),
(4, 'VODKA', 7, 2, NULL),
(5, 'PILSENER', 1, 2, NULL),
(6, 'CAFE', 1, 1, 1),
(7, 'CHOCOLATE', 1.6, 1, 1),
(8, 'TE CALIENTE', 0.75, 1, 1),
(9, 'LICUADOS', 1.15, 2, 1),
(10, 'FROZEN', 1.35, 2, 1),
(11, 'frijol/queso', 0.35, 5, 1),
(12, 'frijol', 0.3, 5, 1),
(13, 'queso', 0.5, 5, 1),
(14, 'ayote', 0.35, 5, 1),
(15, 'revueltas', 0.35, 5, 1),
(16, 'pollo', 0.5, 5, 1),
(17, 'jamon', 0.5, 5, 1),
(18, 'camaron', 0.5, 5, 1),
(19, 'chicharron', 0.35, 5, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle_orden`
--
ALTER TABLE `detalle_orden`
  ADD PRIMARY KEY (`id`,`producto`),
  ADD KEY `id` (`id`) USING BTREE,
  ADD KEY `producto` (`producto`) USING BTREE;

--
-- Indices de la tabla `orden`
--
ALTER TABLE `orden`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `parametros`
--
ALTER TABLE `parametros`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria` (`categoria`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `orden`
--
ALTER TABLE `orden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_orden`
--
ALTER TABLE `detalle_orden`
  ADD CONSTRAINT `detalle_orden_ibfk_1` FOREIGN KEY (`id`) REFERENCES `orden` (`id`),
  ADD CONSTRAINT `detalle_orden_ibfk_2` FOREIGN KEY (`producto`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
