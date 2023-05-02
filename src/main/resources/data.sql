CREATE TABLE PLAY_RESULT (
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    played_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAY_RESULT_DETAIL (
    id              BIGINT         NOT NULL AUTO_INCREMENT,
    play_result_id  BIGINT         NOT NULL,
    name            VARCHAR(50) NOT NULL,
    position        INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id)
);
