DROP TABLE IF EXISTS `USERS`;
CREATE TABLE USERS (
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(64) NOT NULL ,
  password_salt VARCHAR(16) NOT NULL ,
  notes VARCHAR(100) ,
  enabled TINYINT NOT NULL DEFAULT 1,
  role_id INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES USER_ROLES(role_id)
);