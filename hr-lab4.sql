-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hr
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hr` ;

-- -----------------------------------------------------
-- Schema hr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hr` DEFAULT CHARACTER SET latin1 ;
USE `hr` ;

-- -----------------------------------------------------
-- Table `hr`.`countries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hr`.`countries` ;

CREATE TABLE IF NOT EXISTS `hr`.`countries` (
  `country_id` CHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `country_name` VARCHAR(40) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`country_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `hr`.`departments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hr`.`departments` ;

CREATE TABLE IF NOT EXISTS `hr`.`departments` (
  `department_id` INT(4) NOT NULL,
  `department_name` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `countries_country_id` CHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  PRIMARY KEY (`department_id`),
  INDEX `fk_departments_countries1_idx` (`countries_country_id` ASC) VISIBLE,
  CONSTRAINT `fk_departments_countries1`
    FOREIGN KEY (`countries_country_id`)
    REFERENCES `hr`.`countries` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `hr`.`jobs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hr`.`jobs` ;

CREATE TABLE IF NOT EXISTS `hr`.`jobs` (
  `job_id` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `job_title` VARCHAR(35) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `min_salary` INT(6) NULL DEFAULT NULL,
  `max_salary` INT(6) NULL DEFAULT NULL,
  PRIMARY KEY (`job_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `hr`.`employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hr`.`employees` ;

CREATE TABLE IF NOT EXISTS `hr`.`employees` (
  `employee_id` INT(6) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `last_name` VARCHAR(25) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `email` VARCHAR(25) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `password` VARCHAR(65) NULL DEFAULT '$2a$10$nFKnvQ4h.jKhMyEN6LnEBOUTONuXCdJvzIKzYz.PVLpReP5G9NE96',
  `phone_number` VARCHAR(20) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `hire_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00',
  `salary` DECIMAL(8,2) NULL DEFAULT NULL,
  `commission_pct` DECIMAL(2,2) NULL DEFAULT NULL,
  `department_id` INT(4) NULL DEFAULT NULL,
  `enabled` INT(11) NULL DEFAULT '0',
  `jobs_job_id` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `countries_country_id` CHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `emp_email_uk` (`email` ASC) VISIBLE,
  INDEX `emp_department_ix` (`department_id` ASC) VISIBLE,
  INDEX `emp_name_ix` (`last_name` ASC, `first_name` ASC) VISIBLE,
  INDEX `fk_employees_jobs1_idx` (`jobs_job_id` ASC) VISIBLE,
  INDEX `fk_employees_countries1_idx` (`countries_country_id` ASC) VISIBLE,
  CONSTRAINT `emp_dept_fk`
    FOREIGN KEY (`department_id`)
    REFERENCES `hr`.`departments` (`department_id`),
  CONSTRAINT `fk_employees_jobs1`
    FOREIGN KEY (`jobs_job_id`)
    REFERENCES `hr`.`jobs` (`job_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employees_countries1`
    FOREIGN KEY (`countries_country_id`)
    REFERENCES `hr`.`countries` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 207
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `hr`.`job_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hr`.`job_history` ;

CREATE TABLE IF NOT EXISTS `hr`.`job_history` (
  `job_history_id` INT(6) NOT NULL,
  `employee_id` INT(6) NOT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `job_id` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `department_id` INT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`job_history_id`, `employee_id`, `start_date`),
  INDEX `jhist_department_ix` (`department_id` ASC) VISIBLE,
  INDEX `jhist_employee_ix` (`employee_id` ASC) VISIBLE,
  INDEX `jhist_job_ix` (`job_id` ASC) VISIBLE,
  INDEX `jhist_job_fk` (`job_id` ASC) VISIBLE,
  CONSTRAINT `jhist_dept_fk`
    FOREIGN KEY (`department_id`)
    REFERENCES `hr`.`departments` (`department_id`),
  CONSTRAINT `jhist_emp_fk`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hr`.`employees` (`employee_id`),
  CONSTRAINT `jhist_job_fk`
    FOREIGN KEY (`job_id`)
    REFERENCES `hr`.`jobs` (`job_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `hr`.`locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hr`.`locations` ;

CREATE TABLE IF NOT EXISTS `hr`.`locations` (
  `location_id` INT(4) NOT NULL,
  `street_address` VARCHAR(40) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `postal_code` VARCHAR(12) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `city` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `state_province` VARCHAR(25) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `country_id` CHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `departments_department_id` INT(4) NOT NULL,
  PRIMARY KEY (`location_id`),
  INDEX `loc_city_ix` (`city` ASC) VISIBLE,
  INDEX `loc_country_ix` (`country_id` ASC) VISIBLE,
  INDEX `loc_state_province_ix` (`state_province` ASC) VISIBLE,
  INDEX `loc_c_id_fk` (`country_id` ASC) VISIBLE,
  INDEX `fk_locations_departments1_idx` (`departments_department_id` ASC) VISIBLE,
  CONSTRAINT `loc_c_id_fk`
    FOREIGN KEY (`country_id`)
    REFERENCES `hr`.`countries` (`country_id`),
  CONSTRAINT `fk_locations_departments1`
    FOREIGN KEY (`departments_department_id`)
    REFERENCES `hr`.`departments` (`department_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `hr`.`regions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hr`.`regions` ;

CREATE TABLE IF NOT EXISTS `hr`.`regions` (
  `region_id` DECIMAL(22,0) NOT NULL,
  `region_name` VARCHAR(25) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `countries_country_id` CHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  PRIMARY KEY (`region_id`),
  INDEX `fk_regions_countries1_idx` (`countries_country_id` ASC) VISIBLE,
  CONSTRAINT `fk_regions_countries1`
    FOREIGN KEY (`countries_country_id`)
    REFERENCES `hr`.`countries` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
