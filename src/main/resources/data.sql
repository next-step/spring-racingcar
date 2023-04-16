CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL default 0,
    winners     VARCHAR(50) NOT NULL,
    play_time   DATETIME    NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER_RECORD (
     id          INT         NOT NULL AUTO_INCREMENT,
     play_id     INT         NOT NULL default 0,
     player_name  VARCHAR(50) NOT NULL,
     player_position  INT NOT NULL default 0,
     PRIMARY KEY (id)
);
