CREATE DATABASE `banco`;

-- -----------------------------------------------------
-- Table `banco`.`Funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banco`.`Funcionario` ;

CREATE TABLE IF NOT EXISTS `banco`.`Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `endereco` VARCHAR(45) NULL,
  `cpf` VARCHAR(45) NULL,
  `departamento` VARCHAR(45) NULL,
  `salario` DOUBLE NULL DEFAULT 0,
  `senha` VARCHAR(45) NULL,
  `cargo` INT NOT NULL DEFAULT 0,
  `usuario` VARCHAR(45) NULL,
  PRIMARY KEY (`idFuncionario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banco`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `banco`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `endereco` VARCHAR(45) NULL,
  `cpf` VARCHAR(45) NULL,
  `usuario` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco`.`Conta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banco`.`Conta` ;

CREATE TABLE IF NOT EXISTS `banco`.`Conta` (
  `idConta` INT NOT NULL AUTO_INCREMENT,
  `saldo` DOUBLE NOT NULL DEFAULT 0,
  `limite` DOUBLE NOT NULL DEFAULT 0,
  `Cliente_idCliente` INT NOT NULL,
  `tipo` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idConta`, `Cliente_idCliente`),
  INDEX `fk_Conta_Cliente_idx` (`Cliente_idCliente` ASC),
  CONSTRAINT `fk_Conta_Cliente`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `banco`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
