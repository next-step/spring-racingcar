-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAY_RESULT
(
    id          INT          NOT NULL AUTO_INCREMENT,
    trial_count INT          NOT NULL,
    name        VARCHAR(255) NOT NULL,
    position    INT,
    winners     VARCHAR(255) NOT NULL,
    created_at  DATETIME     NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);
