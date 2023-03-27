-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.

CREATE TABLE RACING_INFO (
    ID          INT         NOT NULL AUTO_INCREMENT,
    TRIAL_COUNT INT         NOT NULL,
    WINNERS VARCHAR(255)    NOT NULL,
    PLAY_TIME   DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE RACING_PLAYER  (
    id              INT     NOT NULL AUTO_INCREMENT,
    NAME     VARCHAR(255)   NOT NULL,
    POSITION        INT     NOT NULL default  0,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id, NAME)
);
