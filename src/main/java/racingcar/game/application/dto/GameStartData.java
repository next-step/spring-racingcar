package racingcar.game.application.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import racingcar.game.domain.RacingCar;
import racingcar.game.domain.RacingGame;

@Getter
public class GameStartData {

    private final List<Player> players;
    private final int count;

    public GameStartData(String names, int count) {
        this.players = Arrays.stream(names.split(","))
            .map(Player::new)
            .collect(Collectors.toUnmodifiableList());
        this.count = count;
    }

    public RacingGame toRacingGame() {
        return new RacingGame(players.stream()
            .map(player -> new RacingCar(player.getName()))
            .collect(Collectors.toUnmodifiableList()));
    }
}
