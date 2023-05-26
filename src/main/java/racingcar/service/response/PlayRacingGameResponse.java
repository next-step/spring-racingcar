package racingcar.service.response;

import racingcar.entity.RacingGameResponse;
import racingcar.entity.RacingPlayerResponse;

import java.util.List;

public class PlayRacingGameResponse {

    RacingGameResponse game;
    List<RacingPlayerResponse> players;

    public PlayRacingGameResponse(RacingGameResponse game, List<RacingPlayerResponse> players) {
        this.game = game;
        this.players = players;
    }

    public RacingGameResponse getGame() {
        return game;
    }

    public List<RacingPlayerResponse> getPlayers() {
        return players;
    }
}
