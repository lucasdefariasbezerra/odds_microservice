CREATE TABLE tournment (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE season (
  id int NOT NULL AUTO_INCREMENT,
  name_right varchar(255) NOT NULL,
  tournment_type varchar(255) NOT NULL,
  season_start bigint NOT NULL,
  season_end bigint NOT NULL,
  tournment_id int NOT NULL,
  KEY tournment_id (tournment_id),
  PRIMARY KEY (id),
  CONSTRAINT season_ibfk_1 FOREIGN KEY (tournment_id) REFERENCES tournment (id)
);