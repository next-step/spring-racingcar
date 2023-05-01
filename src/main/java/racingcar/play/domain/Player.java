package racingcar.play.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Player {

    private final Long id;
    private final Long playResultId;
    private final String name;
    private final Integer position;
    private final LocalDateTime createdAt;

    public static Player of(Long playResultId, RacingCar racingCar) {
        return new Player(null, playResultId, racingCar.getName(), racingCar.getPosition(), null);
    }
}
