
DROP TABLE IF EXISTS PLAY_RESULT;
CREATE TABLE PLAY_RESULT
(
    id         INT      NOT NULL AUTO_INCREMENT,
    round      INT      NOT NULL,
    winners    VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS PLAY_CAR_HISTORY;
CREATE TABLE PLAY_CAR_HISTORY
(
    id         INT         NOT NULL,
    name       varchar(50) NOT NULL,
    position   INT         NOT NULL,
    created_at DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id, name)
);