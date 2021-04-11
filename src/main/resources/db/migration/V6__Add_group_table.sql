CREATE TABLE season_group (
  id int NOT NULL AUTO_INCREMENT,
  description varchar(255),
  group_key varchar(255),
  season_id int NOT NULL,
  KEY season_id (season_id),
  PRIMARY KEY (id),
  CONSTRAINT season_group_season_ibfk_1 FOREIGN KEY (season_id) REFERENCES season (id)
);

CREATE TABLE matches (
    id int NOT NULL AUTO_INCREMENT,
    team_home int NOT NULL,
    team_away int NOT NULL,
    score_home int NOT NULL,
    score_away int NOT NULL,
    group_id int NOT NULL,
    processed int DEFAULT 0,
    match_date bigint NOT NULL,
    round int NOT NULL,
    KEY team_home (team_home),
    KEY team_away (team_away),
    KEY group_id (group_id),
    PRIMARY KEY (id),
    CONSTRAINT team_home_ibfk_1 FOREIGN KEY (team_home) REFERENCES team (id),
    CONSTRAINT team_away_ibfk_1 FOREIGN KEY (team_away) REFERENCES team (id),
    CONSTRAINT match_group_ibfk_1 FOREIGN KEY (group_id) REFERENCES season_group (id)
);

CREATE TABLE standings (
    id int NOT NULL AUTO_INCREMENT,
    team_id int NOT NULL,
    group_id int NOT NULL,
    goals_pro int DEFAULT 0,
    goals_con int DEFAULT 0,
    wins int DEFAULT 0,
    lose int DEFAULT 0,
    draw int DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT standings_team_id_ibfk_1 FOREIGN KEY (team_id) REFERENCES team (id),
    CONSTRAINT standings_group_id_ibfk_1 FOREIGN KEY (group_id) REFERENCES season_group (id)
);