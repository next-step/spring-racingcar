CREATE TABLE PLAY_RESULT (
    id           INT         NOT NULL AUTO_INCREMENT,
    WINNERS      VARCHAR(50) NOT NULL,
    trial_count  INT NOT NULL,
    created_at   DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAY_DETAIL_RESULT (
                             id          INT         NOT NULL AUTO_INCREMENT,
                             play_id     INT         NOT NULL ,
                             player      VARCHAR(50) NOT NULL,
                             position    INT NOT NULL,
                             is_winner   BIT NOT NULL,
                             created_at  DATETIME    NOT NULL default current_timestamp,
                             PRIMARY KEY (id)
);
