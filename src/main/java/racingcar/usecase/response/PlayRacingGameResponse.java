package racingcar.usecase.response;

import java.util.List;

public class PlayRacingGameResponse {

    private final RacingGameResponse game;
    private final List<RacingPlayerResponse> players;

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
