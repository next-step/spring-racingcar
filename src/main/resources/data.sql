-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE RACING_GAME (
    TRIAL_COUNT    INT      NOT NULL,
    WINNERS     VARCHAR(50) NOT NULL,
    RACING_DATE  DATETIME    NOT NULL default current_timestamp
);
