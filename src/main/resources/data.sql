-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAY_RESULT
(
    id          INT         NOT NULL AUTO_INCREMENT,
    GROUP_ID    INT         NOT NULL,
    TRIAL_COUNT INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PERSON
(
    id         INT         NOT NULL AUTO_INCREMENT,
    NAME       VARCHAR(50) NOT NULL,
    created_at DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE RACING_CAR
(
    id         INT         NOT NULL AUTO_INCREMENT,
    GROUP_ID   INT         NOT NULL,
    PERSON_ID  INT         NOT NULL,
    NAME       VARCHAR(50) NOT NULL,
    POSITION   INT         NOT NULL DEFAULT 0,
    created_at DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
)
