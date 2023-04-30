package racingcar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RacingGameResult {
    private List<String> winners;
    private List<RacingCarDto> racingCarDtos;

}
