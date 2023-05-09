package racingcar.game.application.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import racingcar.game.domain.PlayerHistoryEntity;
import racingcar.game.domain.RacingCar;

@Getter
public class Player {

    private final String name;
    private final int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public static Player from(PlayerHistoryEntity playerHistoryEntity) {
        return new Player(playerHistoryEntity.getName());
    }

    public static List<Player> from(List<RacingCar> racingCars) {
        return racingCars.stream()
            .map(racingCar -> new Player(racingCar.getName()))
            .collect(Collectors.toUnmodifiableList());
    }
}
