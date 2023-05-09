-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAY_RESULT (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER_HISTORY (
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    play_result_id  BIGINT      NOT NULL,
    name            VARCHAR(10) NOT NULL,
    position        INT         NOT NULL,
    is_winner       BOOLEAN     NOT NULL,
    created_at      DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id) references PLAY_RESULT(id)
);
