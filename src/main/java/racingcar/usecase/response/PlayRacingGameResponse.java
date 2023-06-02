package racingcar.usecase.response;

import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;

import java.util.List;

public class PlayRacingGameResponse {

    private final RacingGame game;
    private final List<RacingPlayer> players;

    public PlayRacingGameResponse(RacingGame game, List<RacingPlayer> players) {
        this.game = game;
        this.players = players;
    }

    public RacingGame getGame() {
        return game;
    }

    public List<RacingPlayer> getPlayers() {
        return players;
    }
}
