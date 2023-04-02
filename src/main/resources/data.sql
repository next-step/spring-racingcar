CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    trial_cnt   INT         NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE PLAY_HISTORY (
    id          INT         NOT NULL AUTO_INCREMENT,
    player      VARCHAR(50) NOT NULL,
    position    INT         NOT NULL,
    played_at   DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
)