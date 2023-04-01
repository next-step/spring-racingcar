DROP TABLE IF EXISTS PLAY_RESULT;
CREATE TABLE PLAY_RESULT (
                             id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             trial_count    INT NOT NULL,
                             winners        VARCHAR(50)  NULL,
                             created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

DROP TABLE IF EXISTS PLAY_HISTORY;
CREATE TABLE PLAY_HISTORY (
                             id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             name           VARCHAR(5)  NULL,
                             move           INT NOT NULL,
                             created_at DATETIME NOT NULL default CURRENT_TIMESTAMP()
);