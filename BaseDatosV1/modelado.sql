-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema bibliotecaonline
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bibliotecaonline
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bibliotecaonline` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaonline`.`Autor` (
  `id_autor` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id_autor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaonline`.`Libro` (
  `id_libro` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(40) NOT NULL,
  `year` VARCHAR(10) NULL,
  `precio` FLOAT NOT NULL,
  `id_autor` VARCHAR(20) NOT NULL,
  `resumen` VARCHAR(200) NULL,
  `is_selled` TINYINT NOT NULL,
  `id_pedido` VARCHAR(20) NULL,
  PRIMARY KEY (`id_libro`),
  INDEX `fk_Libro_Autor_idx` (`id_autor` ASC) VISIBLE,
  INDEX `fk_Libro_Pedido1_idx` (`id_pedido` ASC) VISIBLE,
  CONSTRAINT `fk_Libro_Autor`
    FOREIGN KEY (`id_autor`)
    REFERENCES `bibliotecaonline`.`Autor` (`id_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Libro_Pedido1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `bibliotecaonline`.`Pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria` (
  `id_categoria` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Libro_has_Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaonline`.`Libro_has_Categoria` (
  `id_libro` VARCHAR(20) NOT NULL,
  `id_categoria` VARCHAR(20) NOT NULL,
  INDEX `fk_Libro_has_Categoria_Categoria1_idx` (`id_categoria` ASC) VISIBLE,
  INDEX `fk_Libro_has_Categoria_Libro1_idx` (`id_libro` ASC) VISIBLE,
  CONSTRAINT `fk_Libro_has_Categoria_Libro1`
    FOREIGN KEY (`id_libro`)
    REFERENCES `bibliotecaonline`.`Libro` (`id_libro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Libro_has_Categoria_Categoria1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `bibliotecaonline`.`Categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `bibliotecaonline` ;

-- -----------------------------------------------------
-- Table `bibliotecaonline`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaonline`.`autor` (
  `id_autor` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id_autor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bibliotecaonline`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaonline`.`categoria` (
  `id_categoria` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bibliotecaonline`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaonline`.`pedido` (
  `id_pedido` VARCHAR(20) NOT NULL,
  `nombre_comprador` VARCHAR(40) NOT NULL,
  `telefono_comprador` VARCHAR(15) NOT NULL,
  `direccion_entrega` VARCHAR(30) NOT NULL,
  `direccion_ip` VARCHAR(20) NOT NULL,
  `app_pago` VARCHAR(20) NOT NULL,
  `fecha_pedido` DATE NOT NULL,
  `valor_compra` FLOAT NOT NULL,
  `respuesta_pago` VARCHAR(100) NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_pedido`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bibliotecaonline`.`libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaonline`.`libro` (
  `id_libro` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(40) NOT NULL,
  `year` VARCHAR(10) NULL DEFAULT NULL,
  `precio` FLOAT NOT NULL,
  `id_autor` VARCHAR(20) NOT NULL,
  `resumen` VARCHAR(200) NULL DEFAULT NULL,
  `is_selled` TINYINT NOT NULL,
  `id_pedido` VARCHAR(20) ,
  PRIMARY KEY (`id_libro`, `id_pedido`),
  INDEX `fk_Libro_Autor_idx` (`id_autor` ASC) VISIBLE,
  INDEX `fk_Libro_Pedido1_idx` (`id_pedido` ASC) VISIBLE,
  CONSTRAINT `fk_Libro_Autor`
    FOREIGN KEY (`id_autor`)
    REFERENCES `bibliotecaonline`.`autor` (`id_autor`),
  CONSTRAINT `fk_Libro_Pedido1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `bibliotecaonline`.`pedido` (`id_pedido`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bibliotecaonline`.`libro_has_categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecaonline`.`libro_has_categoria` (
  `id_libro` VARCHAR(20) NOT NULL,
  `id_categoria` VARCHAR(20) NOT NULL,
  INDEX `fk_Libro_has_Categoria_Categoria1_idx` (`id_categoria` ASC) VISIBLE,
  INDEX `fk_Libro_has_Categoria_Libro1_idx` (`id_libro` ASC) VISIBLE,
  CONSTRAINT `fk_Libro_has_Categoria_Categoria1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `bibliotecaonline`.`categoria` (`id_categoria`),
  CONSTRAINT `fk_Libro_has_Categoria_Libro1`
    FOREIGN KEY (`id_libro`)
    REFERENCES `bibliotecaonline`.`libro` (`id_libro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
