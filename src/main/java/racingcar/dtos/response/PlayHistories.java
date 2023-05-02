package racingcar.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlayHistories {
    private String winners;
    private List<PlayRacingCar> racingCars;
}
