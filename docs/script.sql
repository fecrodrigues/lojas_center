-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema loja_center
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema loja_center
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `loja_center` DEFAULT CHARACTER SET utf8 ;
USE `loja_center` ;

-- -----------------------------------------------------
-- Table `loja_center`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_center`.`cliente` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(170) NULL,
  `data_nascimento` DATE NULL,
  `cpf` VARCHAR(11) NULL,
  `nome_mae` VARCHAR(170) NULL,
  `email` VARCHAR(200) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_center`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_center`.`pedido` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigo_cliente` INT NOT NULL,
  `data_compra` DATETIME NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Pedido_Cliente1_idx` (`codigo_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Cliente1`
    FOREIGN KEY (`codigo_cliente`)
    REFERENCES `loja_center`.`cliente` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_center`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_center`.`produto` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `quantidade` INT NOT NULL,
  `valor` DECIMAL NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_center`.`pedido_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_center`.`pedido_produto` (
  `codigo_pedido` INT NOT NULL,
  `codigo_produto` INT NOT NULL,
  PRIMARY KEY (`codigo_pedido`, `codigo_produto`),
  INDEX `fk_Pedido_has_Produto_Produto1_idx` (`codigo_produto` ASC) VISIBLE,
  INDEX `fk_Pedido_has_Produto_Pedido_idx` (`codigo_pedido` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_has_Produto_Pedido`
    FOREIGN KEY (`codigo_pedido`)
    REFERENCES `loja_center`.`pedido` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_has_Produto_Produto1`
    FOREIGN KEY (`codigo_produto`)
    REFERENCES `loja_center`.`produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_center`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_center`.`endereco` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `cep` VARCHAR(8) NULL,
  `logradouro` VARCHAR(170) NULL,
  `cidade` VARCHAR(150) NULL,
  `estado` VARCHAR(150) NULL,
  `numero` INT NULL,
  `complemento` VARCHAR(255) NULL,
  `codigo_cliente` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Endereco_Cliente1_idx` (`codigo_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_Endereco_Cliente1`
    FOREIGN KEY (`codigo_cliente`)
    REFERENCES `loja_center`.`cliente` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
