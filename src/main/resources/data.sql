-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
DROP TABLE IF EXISTS PLAY_RESULT;
CREATE TABLE RACING_RESULT (
                             id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             trial_count    INT NOT NULL,
                             winners        VARCHAR(50)  NULL,
                             work_detail_tmst DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

DROP TABLE IF EXISTS RACING_RECORD;
CREATE TABLE RACING_RECORD (
                             id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             name           VARCHAR(5)  NULL,
                             move           INT NOT NULL,
                             work_detail_tmst DATETIME NOT NULL default CURRENT_TIMESTAMP()
);
