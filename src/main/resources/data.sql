-- GAME
CREATE TABLE GAME_HISTORY
(
    id          INT          NOT NULL AUTO_INCREMENT,
    trial_count INT          NOT NULL,
    car_names   VARCHAR(255) NOT NULL,
    winners     VARCHAR(255),
    created_at  DATETIME DEFAULT current_timestamp,

    PRIMARY KEY (id)


);
CREATE TABLE ROUND_HISTORY
(
    id       INT         NOT NULL AUTO_INCREMENT,
    game_id  INT,
    round    INT         NOT NULL,
    name     VARCHAR(20) NOT NULL,
    position INT         NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES GAME_HISTORY (id)
);
