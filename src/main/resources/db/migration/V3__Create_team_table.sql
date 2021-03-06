CREATE TABLE team (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  sport_id int NOT NULL,
  country_id int NOT NULL,
  PRIMARY KEY (id),
  KEY sport_id (sport_id),
  KEY country_id (country_id),
  UNIQUE(country_id, name),
  CONSTRAINT team_ibfk_1 FOREIGN KEY (sport_id) REFERENCES sport (id),
  CONSTRAINT team_ibfk_2 FOREIGN KEY (country_id) REFERENCES country (id)
);

INSERT INTO team VALUES
(1,'Chelsea',1, 58),
(2,'Manchester City',1, 58),
(3,'Manchester United',1, 58),
(4,'Arsenal',1, 58),
(5,'Everton',1, 58),
(6,'West Ham',1, 58),
(7,'Brighton',1, 58),
(8,'Hudersfield Town',1, 58),
(9,'Burnley',1, 58),
(10,'Bornemouth',1, 58),
(11,'Liverpool',1, 58),
(12,'Tottenham',1, 58),
(13,'Crystal Palace',1, 58),
(14,'Watford',1, 58),
(15,'Newcastle',1, 58),
(16,'Fulham',1, 58),
(17,'Leicester',1, 58),
(18,'Southampton',1, 58),
(19,'Wolverhampton',1, 58),
(20,'Barcelona',1, 47),
(21,'Real Madrid',1, 47),
(22,'Atletico Madrid',1, 47),
(23,'Valencia',1, 47),
(24,'Villareal',1, 47),
(25,'Rayo Valecano',1, 47),
(26,'Eibar',1, 47),
(27,'Levante',1, 47),
(29,'Girona',1, 47),
(30,'Celta de Vigo',1, 47),
(31,'Getafe',1, 47),
(32,'Betis',1, 47),
(33,'Sevilha',1, 47),
(34,'Espanyol',1, 47),
(35,'Huesca',1, 47),
(36,'Real Sociedad',1, 47),
(37,'Athletic Bilbao',1, 47),
(38,'Alaves',1, 47),
(110,'Borussia Dortmund',1, 20),
(111,'Bayern Munchen',1, 20);