-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE winner_history (
    id          INT         NOT NULL AUTO_INCREMENT,
    round       INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);
CREATE TABLE racing_history(
    id          INT             NOT NULL AUTO_INCREMENT,
    round       INT             NOT NULL,
    trial_count INT             NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    position    INT             NOT NULL,
    created_at  DATETIME        default current_timestamp
);