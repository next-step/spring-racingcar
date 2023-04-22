CREATE TABLE RACING_RESULT (
                             race_round          INT         NOT NULL,
                             winners     VARCHAR(50) NOT NULL,
                             created_at  DATETIME    NOT NULL default current_timestamp,
                             trial_count   INT         NOT NULL,
                             PRIMARY KEY (race_round)
);


CREATE TABLE RACING_HISTORY (
                              id          INT         NOT NULL AUTO_INCREMENT,
                              race_round  INT         NOT NULL,
                              player      VARCHAR(50) NOT NULL,
                              position    INT         NOT NULL,
                              played_at   DATETIME    NOT NULL default current_timestamp,
                              PRIMARY KEY (id)
)