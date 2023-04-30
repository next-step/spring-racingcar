CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER_FINAL_TRAVEL_DISTANCE (
    id          INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(20) NOT NULL ,
    position    INT         NOT NULL ,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
)
