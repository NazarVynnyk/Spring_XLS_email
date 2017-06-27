-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_group` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_group` (
  `id` INT(10) NOT NULL,
  `group_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`country` ;

CREATE TABLE IF NOT EXISTS `mydb`.`country` (
  `id` INT(10) NOT NULL,
  `country_name` VARCHAR(50) NOT NULL,
  `language` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `country_name_UNIQUE` (`country_name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`location` ;

CREATE TABLE IF NOT EXISTS `mydb`.`location` (
  `id` INT(10) NOT NULL,
  `location_name` VARCHAR(50) NOT NULL,
  `country_id` INT(10) NOT NULL,
  `latitude` DECIMAL NULL,
  `longitude` DECIMAL NULL,
  PRIMARY KEY (`id`),
  INDEX `fc_country_idx` (`country_id` ASC),
  CONSTRAINT `fc_country`
  FOREIGN KEY (`country_id`)
  REFERENCES `mydb`.`country` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT(10) NOT NULL,
  `user_name` INT(10) NOT NULL,
  `location_id` INT(10) NOT NULL,
  `user_group_id` INT(10) NOT NULL,
  PRIMARY KEY (`id`, `user_group_id`),
  INDEX `fc_user_group_idx` (`user_group_id` ASC),
  INDEX `fc_location_idx` (`location_id` ASC),
  UNIQUE INDEX `name_group` (`user_name` ASC, `user_group_id` ASC),
  CONSTRAINT `fc_user_group`
  FOREIGN KEY (`user_group_id`)
  REFERENCES `mydb`.`user_group` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fc_location`
  FOREIGN KEY (`location_id`)
  REFERENCES `mydb`.`location` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`session` ;

CREATE TABLE IF NOT EXISTS `mydb`.`session` (
  `id` INT(10) NOT NULL,
  `user_id` INT(10) NOT NULL,
  `date_opened` DATETIME NULL,
  `date_closed` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `fc_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `mydb`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`request` ;

CREATE TABLE IF NOT EXISTS `mydb`.`request` (
  `id` INT(10) NOT NULL,
  `url` VARCHAR(100) NULL,
  `method` VARCHAR(10) NULL,
  `params` TEXT(100) NULL,
  `session_id` INT(10) NULL,
  PRIMARY KEY (`id`),
  INDEX `session_id_idx` (`session_id` ASC),
  CONSTRAINT `fc_session`
  FOREIGN KEY (`session_id`)
  REFERENCES `mydb`.`session` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
