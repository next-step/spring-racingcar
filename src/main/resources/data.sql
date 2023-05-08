CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    winner     VARCHAR(50)  NOT NULL,
    game        BIGINT      NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAY_FINAL_POSITION_GAME (
    id          INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(20) NOT NULL ,
    position    INT         NOT NULL ,
    game        BIGINT      NOT NULL ,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
)
