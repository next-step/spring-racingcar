CREATE TABLE IF NOT EXISTS RECORDS (
    id          INT         NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    racing_cars TEXT        NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);
