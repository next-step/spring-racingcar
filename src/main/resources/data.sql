CREATE TABLE GAME_RESULT (
    id          long         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);
CREATE TABLE GAME_HISTORY
(
    id             long         NOT NULL AUTO_INCREMENT,
    play_result_id INT         NOT NULL,
    name           VARCHAR(50) NOT NULL,
    position       INT         NOT NULL,
    created_at     DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id) REFERENCES GAME_RESULT (id)
);
