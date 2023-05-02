package racingcar.play.api.dto;

import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import racingcar.play.domain.RacingCar;
import racingcar.play.domain.RacingCars;

@AllArgsConstructor
@Getter
public class PlayRequest {

    private String names;
    private Integer count;

    public RacingCars toRacingCars() {
        return new RacingCars(Arrays.stream(names.split(","))
            .map(RacingCar::new)
            .collect(Collectors.toUnmodifiableList()));
    }
}
