-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE racing_games
(
    racing_game_id LONG     NOT NULL AUTO_INCREMENT,
    trial_count     INT      NOT NULL,
    created_date   DATETIME NOT NULL,
    PRIMARY KEY (racing_game_id)
);


CREATE TABLE racing_players
(
    racing_player_id LONG        NOT NULL AUTO_INCREMENT,
    racing_game_id   LONG        NOT NULL,
    name             VARCHAR(20) NOT NULL,
    position         INT         NOT NULL,
    is_winner        BOOLEAN     NOT NULL,
    created_date     DATETIME    NOT NULL,
    PRIMARY KEY (racing_player_id),
    FOREIGN KEY (racing_game_id) REFERENCES racing_games (racing_game_id)

);
