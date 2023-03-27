DROP TABLE IF EXISTS PLAY_RESULT;
CREATE TABLE PLAY_RESULT (
     id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     trial_count    INT            NOT NULL,
     players        OBJECT ARRAY   NOT NULL,
     winners        OBJECT ARRAY   NOT NULL,
     created_at     DATETIME       default current_timestamp
);