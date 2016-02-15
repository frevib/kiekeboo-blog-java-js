-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema kb_database
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `kb_database` ;

-- -----------------------------------------------------
-- Schema kb_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kb_database` DEFAULT CHARACTER SET utf8 ;
USE `kb_database` ;

-- -----------------------------------------------------
-- Table `kb_database`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kb_database`.`user_roles` ;

CREATE TABLE IF NOT EXISTS `kb_database`.`user_roles` (
  `id_role` INT(11) NOT NULL,
  `role` VARCHAR(30) NOT NULL,
  `role_description` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id_role`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kb_database`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kb_database`.`users` ;

CREATE TABLE IF NOT EXISTS `kb_database`.`users` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT,
  `id_role` INT(11) NOT NULL,
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `salt` VARCHAR(8) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`),
  CONSTRAINT `id_role`
  FOREIGN KEY (`id_role`)
  REFERENCES `kb_database`.`user_roles` (`id_role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_users_user_roles_idx` ON `kb_database`.`users` (`id_role` ASC);


-- -----------------------------------------------------
-- Table `kb_database`.`blogposts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kb_database`.`blogposts` ;

CREATE TABLE IF NOT EXISTS `kb_database`.`blogposts` (
  `id_blogpost` INT(11) NOT NULL AUTO_INCREMENT,
  `id_author` INT(11) NOT NULL,
  `title` VARCHAR(30) NOT NULL,
  `contents` VARCHAR(1000) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT '1',
  `comments_enabled` TINYINT(1) NOT NULL DEFAULT '1',
  `views` INT(11) NOT NULL DEFAULT 0,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_blogpost`),
  CONSTRAINT `id_author`
  FOREIGN KEY (`id_author`)
  REFERENCES `kb_database`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_blogposts_users1_idx` ON `kb_database`.`blogposts` (`id_author` ASC);


-- -----------------------------------------------------
-- Table `kb_database`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kb_database`.`comments` ;

CREATE TABLE IF NOT EXISTS `kb_database`.`comments` (
  `id_comment` INT(11) NOT NULL AUTO_INCREMENT,
  `id_belongs_blogpost` INT(11) NOT NULL,
  `author` VARCHAR(30) NOT NULL,
  `comment_contents` VARCHAR(300) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_comment`),
  CONSTRAINT `id_belongs_blogpost`
  FOREIGN KEY (`id_belongs_blogpost`)
  REFERENCES `kb_database`.`blogposts` (`id_blogpost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_comments_blogposts1_idx` ON `kb_database`.`comments` (`id_belongs_blogpost` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
