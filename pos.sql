-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Nov 07, 2019 at 09:31 PM
-- Server version: 5.6.45
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `calcularTotalOrden` (`LidOrden` INT)  BEGIN

update orden set total=(select sum(d.precioUnitario * d.cantidad) from detalleorden d where d.idOrden=LidOrden) where id=LidOrden;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bitacora`
--

CREATE TABLE `bitacora` (
  `id` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `suceso` varchar(245) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bitacora`
--

INSERT INTO `bitacora` (`id`, `idUsuario`, `fecha`, `suceso`) VALUES
(1, 1, '2019-10-04 18:14:31', 'Compro pan'),
(2, 1, '2019-10-07 18:14:33', 'Tradeo pan'),
(3, 2, '2019-10-01 18:15:02', 'Vendio tierra'),
(4, 1, '2019-10-19 18:15:02', 'Encontro diamantes'),
(5, 1, '2019-10-10 18:15:32', 'Murio por un creeper'),
(6, 1, '2019-09-09 18:15:32', 'Murio por una bruja');

--
-- Triggers `bitacora`
--
DELIMITER $$
CREATE TRIGGER `bitacora_BEFORE_INSERT` BEFORE INSERT ON `bitacora` FOR EACH ROW BEGIN
SET NEW.fecha = now();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(1, 'Entradas'),
(2, 'Platos'),
(3, 'Bebidas');

-- --------------------------------------------------------

--
-- Table structure for table `compra`
--

CREATE TABLE `compra` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `Ndocumento` varchar(45) DEFAULT NULL,
  `NRC` varchar(45) DEFAULT NULL,
  `NITDUI` varchar(45) DEFAULT NULL,
  `nombreProveedor` varchar(145) NOT NULL,
  `montoInterno` decimal(10,4) NOT NULL,
  `iva` decimal(8,2) NOT NULL,
  `percepcion` decimal(8,2) NOT NULL,
  `total` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compra`
--

INSERT INTO `compra` (`id`, `fecha`, `Ndocumento`, `NRC`, `NITDUI`, `nombreProveedor`, `montoInterno`, `iva`, `percepcion`, `total`) VALUES
(1, '2019-10-06', '234567890', '3245678', '34567', 'Juanito', '10.8000', '1.05', '5.00', '70.80'),
(2, '2019-10-06', '4567890-', '23434423', '34354543545', 'Jose', '6.0000', '3.00', '3.00', '234.90');

-- --------------------------------------------------------

--
-- Stand-in structure for view `dashboardllevar`
-- (See below for the actual view)
--
CREATE TABLE `dashboardllevar` (
`IdOrden` int(11)
,`Mesero` varchar(145)
,`Cliente` varchar(145)
,`Total` decimal(8,2)
,`Estado` varchar(2)
,`TiempoPreparado` varchar(17)
,`Preparado` varchar(6)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `dashboardprincipal`
-- (See below for the actual view)
--
CREATE TABLE `dashboardprincipal` (
`IdOrden` int(11)
,`Mesa` int(11)
,`Mesero` varchar(145)
,`Cliente` varchar(145)
,`Total` decimal(8,2)
,`Estado` varchar(2)
,`llevar` int(11)
,`TiempoPreparado` varchar(17)
,`Preparado` varchar(6)
,`TiempoRapido` varchar(17)
,`Rapido` varchar(6)
,`tipo` varchar(6)
);

-- --------------------------------------------------------

--
-- Table structure for table `detallecompra`
--

CREATE TABLE `detallecompra` (
  `idCompra` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioUnitario` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `detallecompra`
--
DELIMITER $$
CREATE TRIGGER `detallecompra_AFTER_INSERT` AFTER INSERT ON `detallecompra` FOR EACH ROW BEGIN
update producto set inventario=inventario+new.cantidad where id=new.idProducto;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detalleorden`
--

CREATE TABLE `detalleorden` (
  `idOrden` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precioUnitario` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `detalleorden`
--

INSERT INTO `detalleorden` (`idOrden`, `idProducto`, `cantidad`, `precioUnitario`) VALUES
(1, 1, 4, '4.50'),
(1, 2, 6, '1.25'),
(2, 1, 3, '5.00'),
(2, 2, 5, '1.00'),
(3, 1, 2, '5.00'),
(3, 2, 10, '1.00'),
(4, 1, 1, '4.50'),
(5, 1, 2, '4.50'),
(6, 2, 3, '1.25'),
(7, 2, 2, '1.25'),
(9, 1, 5, '4.50'),
(10, 2, 1, '1.25'),
(11, 1, 1, '4.50'),
(11, 2, 1, '1.25');

--
-- Triggers `detalleorden`
--
DELIMITER $$
CREATE TRIGGER `detalleorden_AFTER_DELETE` AFTER DELETE ON `detalleorden` FOR EACH ROW BEGIN
	call calcularTotalOrden(old.idOrden);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `detalleorden_AFTER_INSERT` AFTER INSERT ON `detalleorden` FOR EACH ROW BEGIN
declare tipoP int;
declare Linventario int;
	set tipoP=(select preparado from producto where id=new.idProducto);
	if tipoP=1 then
		update orden set tiempoPreparado=now() where id=new.idOrden;
    else
    		update orden set tiempoRapido=now() where id=new.idOrden;
    end if;
    set Linventario=(select inventario from producto where id=new.idProducto);
    set Linventario=Linventario - new.cantidad;
    if Linventario<0 then
		set Linventario=0;
	end if;
    update producto set inventario=Linventario where id=new.idProducto;
    call calcularTotalOrden(new.idOrden);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `detalleorden_AFTER_UPDATE` AFTER UPDATE ON `detalleorden` FOR EACH ROW BEGIN
declare tipoP int;
	set tipoP=(select preparado from producto where id=new.idProducto);
	if tipoP=1 then
		update orden set tiempoPreparado=now() where id=new.idOrden;
    else
    		update orden set tiempoRapido=now() where id=new.idOrden;
    end if;
    
    call calcularTotalOrden(new.idOrden);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `detalleorden_BEFORE_UPDATE` BEFORE UPDATE ON `detalleorden` FOR EACH ROW BEGIN
declare Linventario int;
set Linventario=(select inventario from producto where id=new.idProducto);
    set Linventario=Linventario - (new.cantidad - old.cantidad);
    if Linventario<0 then
		set Linventario=0;
	end if;
    update producto set inventario=Linventario where id=new.idProducto;

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `mesa`
--

CREATE TABLE `mesa` (
  `id` int(11) NOT NULL,
  `mesa` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mesa`
--

INSERT INTO `mesa` (`id`, `mesa`) VALUES
(1, 'Uno'),
(2, 'Dos'),
(3, 'Tres'),
(4, 'Cuatro'),
(5, 'Cinco');

-- --------------------------------------------------------

--
-- Table structure for table `orden`
--

CREATE TABLE `orden` (
  `id` int(11) NOT NULL,
  `idMesa` int(11) DEFAULT NULL COMMENT 'Cuando la orden es para llevar, la mesa es NULL',
  `idUsuario` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `llevar` int(11) NOT NULL DEFAULT '0',
  `estado` varchar(2) NOT NULL COMMENT 'CC -- Cobrado y Cerrado\nCP -- Cobrado en preparacion\nAA -- Activa sin Cobrar',
  `observacion` varchar(245) DEFAULT NULL,
  `tiempoPreparado` datetime DEFAULT NULL,
  `tiempoRapido` datetime DEFAULT NULL,
  `total` decimal(8,2) DEFAULT '0.00',
  `propina` decimal(8,2) DEFAULT '0.00',
  `formaPago` varchar(1) DEFAULT 'E' COMMENT 'Indica la forma de pago:\nE--Efectivo\nT--Tarjeta de Credito',
  `cliente` varchar(145) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orden`
--

INSERT INTO `orden` (`id`, `idMesa`, `idUsuario`, `fecha`, `llevar`, `estado`, `observacion`, `tiempoPreparado`, `tiempoRapido`, `total`, `propina`, `formaPago`, `cliente`) VALUES
(1, 1, 1, NULL, 1, 'AA', NULL, '2019-11-06 00:09:00', '2019-11-06 00:00:50', '25.50', '0.00', 'E', 'Jose'),
(2, 4, 2, '2019-10-06', 0, 'CP', NULL, NULL, NULL, '20.00', '2.00', 'E', 'Amilcar'),
(3, 2, 2, '2019-10-03', 0, 'CC', NULL, '2019-10-06 00:10:04', NULL, '20.00', '2.00', 'E', 'Marco'),
(4, 2, 1, '2019-11-06', 0, 'AA', '', NULL, NULL, '4.50', '0.00', 'E', 'Pedro'),
(5, 2, 1, '2019-11-07', 0, 'AA', '', NULL, NULL, '9.00', '0.00', 'E', ''),
(6, 2, 2, '2019-11-07', 0, 'AA', '', NULL, NULL, '3.75', '0.00', 'E', ''),
(7, 1, 1, '2019-11-07', 0, 'AA', '', NULL, '2019-11-07 02:30:33', '2.50', '0.00', 'E', ''),
(8, 5, 2, '2019-11-07', 1, 'AA', 'ninguna', NULL, NULL, '0.00', '0.00', 'E', NULL),
(9, 1, 2, '2019-11-07', 1, 'AA', '', NULL, NULL, '22.50', '0.00', 'E', ''),
(10, 1, 1, '2019-11-07', 1, 'AA', '', NULL, '2019-11-07 02:36:41', '1.25', '0.00', 'E', 'Luis'),
(11, 1, 2, '2019-11-07', 1, 'AA', '', '2019-11-07 02:38:29', '2019-11-07 02:38:29', '5.75', '0.00', 'E', '');

--
-- Triggers `orden`
--
DELIMITER $$
CREATE TRIGGER `orden_BEFORE_INSERT` BEFORE INSERT ON `orden` FOR EACH ROW BEGIN
SET NEW.fecha = now();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `parametro`
--

CREATE TABLE `parametro` (
  `id` int(11) NOT NULL,
  `nombre` varchar(145) NOT NULL,
  `valor` varchar(245) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `parametro`
--

INSERT INTO `parametro` (`id`, `nombre`, `valor`) VALUES
(1, 'ModoEntorno', 'MESA'),
(2, 'Nombre', 'La papaya que quema'),
(3, 'Descripcion', 'Servicios de Cafetería y restaurante'),
(4, 'Telefono', '(503) 2453-5478'),
(5, 'NIT', '0524-045374-102-8'),
(6, 'Giro', 'Restaurante'),
(7, 'Direccion', 'Barrio El Calvario calle libertad N23 Santa Ana'),
(8, 'Imprimir Ticket de productos preparados', 'SI'),
(9, 'Imprimir Ticket de productos NO preparados o rapidos', 'SI'),
(10, 'Tiempo maximo ordenes RAPIDAS (minutos)', '4.5'),
(11, 'Tiempo maximo Preparacion de Orden', '18'),
(12, 'Login en cada pantalla', '1'),
(13, 'NRC', '2134567'),
(14, 'Propina', '0.1');

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(145) NOT NULL,
  `precio` decimal(8,2) NOT NULL DEFAULT '0.00',
  `inventario` int(11) NOT NULL DEFAULT '0',
  `preparado` int(11) NOT NULL DEFAULT '1',
  `idCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`, `inventario`, `preparado`, `idCategoria`) VALUES
(1, 'Carne Asada', '5.00', 0, 1, 2),
(2, 'Soda Coca-Cola 12onz', '1.25', 18, 0, 3);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombreCompleto` varchar(145) NOT NULL,
  `login` varchar(45) NOT NULL,
  `clave` varchar(245) NOT NULL,
  `pin` varchar(5) NOT NULL,
  `rol` varchar(1) NOT NULL DEFAULT 'M' COMMENT 'G--Gerente\nM--Mesero'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `nombreCompleto`, `login`, `clave`, `pin`, `rol`) VALUES
(1, 'Administrador', 'admin', 'admin', '12345', 'G'),
(2, 'Juan Perez', 'juan', 'dethmo', '1514', 'M');

-- --------------------------------------------------------

--
-- Stand-in structure for view `ventas`
-- (See below for the actual view)
--
CREATE TABLE `ventas` (
`fecha` date
,`inicial` int(11)
,`final` int(11)
,`ventas` decimal(30,2)
,`propinas` decimal(30,2)
,`total` decimal(31,2)
);

-- --------------------------------------------------------

--
-- Structure for view `dashboardllevar`
--
DROP TABLE IF EXISTS `dashboardllevar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dashboardllevar`  AS  select `o`.`id` AS `IdOrden`,`u`.`nombreCompleto` AS `Mesero`,ifnull(`o`.`cliente`,'') AS `Cliente`,`o`.`total` AS `Total`,`o`.`estado` AS `Estado`,date_format(`o`.`tiempoPreparado`,'%H:%i:%s min') AS `TiempoPreparado`,if(`o`.`tiempoPreparado`,ifnull(if((round(((now() - `o`.`tiempoPreparado`) / 60),1) > (select `parametro`.`valor` from `parametro` where (`parametro`.`id` = 11))),'red',NULL),if((((select `parametro`.`valor` from `parametro` where (`parametro`.`id` = 11)) - round(((now() - `o`.`tiempoPreparado`) / 60),1)) > 1.5),'green','yellow')),'gray') AS `Preparado` from (`orden` `o` join `usuario` `u` on((`o`.`idUsuario` = `u`.`id`))) where ((`o`.`estado` <> 'CC') and (`o`.`llevar` = 1)) order by 1 ;

-- --------------------------------------------------------

--
-- Structure for view `dashboardprincipal`
--
DROP TABLE IF EXISTS `dashboardprincipal`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dashboardprincipal`  AS  select `o`.`id` AS `IdOrden`,`o`.`idMesa` AS `Mesa`,`u`.`nombreCompleto` AS `Mesero`,ifnull(`o`.`cliente`,'') AS `Cliente`,`o`.`total` AS `Total`,`o`.`estado` AS `Estado`,`o`.`llevar` AS `llevar`,date_format(`o`.`tiempoPreparado`,'%H:%i:%s min') AS `TiempoPreparado`,if(`o`.`tiempoPreparado`,ifnull(if((round(((now() - `o`.`tiempoPreparado`) / 60),1) > (select `parametro`.`valor` from `parametro` where (`parametro`.`id` = 11))),'red',NULL),if((((select `parametro`.`valor` from `parametro` where (`parametro`.`id` = 11)) - round(((now() - `o`.`tiempoPreparado`) / 60),1)) > 1.5),'green','yellow')),'gray') AS `Preparado`,date_format(`o`.`tiempoRapido`,'%H:%i:%s min') AS `TiempoRapido`,if(`o`.`tiempoRapido`,ifnull(if((round(((now() - `o`.`tiempoRapido`) / 60),1) > (select `parametro`.`valor` from `parametro` where (`parametro`.`id` = 10))),'red',NULL),if((((select `parametro`.`valor` from `parametro` where (`parametro`.`id` = 10)) - round(((now() - `o`.`tiempoRapido`) / 60),1)) > 1.5),'green','yellow')),'gray') AS `Rapido`,if((`o`.`llevar` = 1),'LLEVAR','AQUI') AS `tipo` from (`orden` `o` join `usuario` `u` on((`o`.`idUsuario` = `u`.`id`))) where (`o`.`estado` <> 'CC') order by 1 ;

-- --------------------------------------------------------

--
-- Structure for view `ventas`
--
DROP TABLE IF EXISTS `ventas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `ventas`  AS  select `o`.`fecha` AS `fecha`,min(`o`.`id`) AS `inicial`,max(`o`.`id`) AS `final`,sum(`o`.`total`) AS `ventas`,sum(`o`.`propina`) AS `propinas`,(sum(`o`.`propina`) + sum(`o`.`total`)) AS `total` from `orden` `o` group by `o`.`fecha` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bitacora`
--
ALTER TABLE `bitacora`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_bitacora_usuario_idx` (`idUsuario`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detallecompra`
--
ALTER TABLE `detallecompra`
  ADD PRIMARY KEY (`idCompra`,`idProducto`),
  ADD KEY `fk_detallecompra_producto_idx` (`idProducto`);

--
-- Indexes for table `detalleorden`
--
ALTER TABLE `detalleorden`
  ADD PRIMARY KEY (`idOrden`,`idProducto`),
  ADD KEY `fk_detalle_producto_idx` (`idProducto`);

--
-- Indexes for table `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orden`
--
ALTER TABLE `orden`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_orden_usuario1_idx` (`idUsuario`),
  ADD KEY `fk_orden_mesa_idx` (`idMesa`);

--
-- Indexes for table `parametro`
--
ALTER TABLE `parametro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_producto_categoria_idx` (`idCategoria`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `loggin_UNIQUE` (`login`),
  ADD UNIQUE KEY `pin_UNIQUE` (`pin`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bitacora`
--
ALTER TABLE `bitacora`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mesa`
--
ALTER TABLE `mesa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `orden`
--
ALTER TABLE `orden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bitacora`
--
ALTER TABLE `bitacora`
  ADD CONSTRAINT `fk_bitacora_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detallecompra`
--
ALTER TABLE `detallecompra`
  ADD CONSTRAINT `fk_detallecompra_compra` FOREIGN KEY (`idCompra`) REFERENCES `compra` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_detallecompra_producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `detalleorden`
--
ALTER TABLE `detalleorden`
  ADD CONSTRAINT `fk_detalle_orden` FOREIGN KEY (`idOrden`) REFERENCES `orden` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_detalle_producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `orden`
--
ALTER TABLE `orden`
  ADD CONSTRAINT `fk_orden_mesa` FOREIGN KEY (`idMesa`) REFERENCES `mesa` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_orden_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
