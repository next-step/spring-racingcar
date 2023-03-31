DROP TABLE IF EXISTS PLAY_RESULT;
CREATE TABLE RACING_CAR (
                            ID          INT         NOT NULL AUTO_INCREMENT,
                            WINNERS     VARCHAR(50) NOT NULL,
                            TRIALCOUNT INT         NOT NULL,
                            CREATED_AT  DATETIME    NOT NULL default current_timestamp,
                            PRIMARY KEY (ID)
);