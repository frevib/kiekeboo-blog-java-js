DROP TABLE IF EXISTS `USER_ROLES`;
CREATE TABLE USER_ROLES (
  role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  role varchar(45) NOT NULL,
  description VARCHAR(80)
);