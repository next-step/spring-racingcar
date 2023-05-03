-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAY_RESULT (
    play_id          INT         NOT NULL AUTO_INCREMENT,
    trial_count  INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (play_id)
);

CREATE TABLE PLAY_CAR_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    position    INT    NOT NULL,
    play_result_id        INT    NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id) references play_result (play_id)

    );

