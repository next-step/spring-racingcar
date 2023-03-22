-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
DROP TABLE PLAY_RESULT IF EXISTS;

CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

DROP TABLE PLAY_HISTORY IF EXISTS;

CREATE TABLE PLAY_HISTORY (
    id          INT         NOT NULL AUTO_INCREMENT,
    turn        INT         NOT NULL,
    name        VARCHAR(50) NOT NULL,
    position    INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);


-- INSERT INTO PLAY_RESULT (id, winners, trial_count) VALUES (1, 'apple', 10);
-- INSERT INTO PLAY_RESULT (id, winners, trial_count) VALUES (2, 'banana', 10);

-- INSERT INTO PLAY_HISTORY(id, turn, name, position) VALUES (1, 1, 'apple', 10);
-- INSERT INTO PLAY_HISTORY(id, turn, name, position) VALUES (2, 1, 'banana', 10);