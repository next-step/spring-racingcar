package racingcar.dto;

import racingcar.domain.RacingCar;
import lombok.Getter;

import java.util.List;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Getter
public class RacingPlaysResponse {
    private String winners;
    private List<RacingCar> racingCars;
}
