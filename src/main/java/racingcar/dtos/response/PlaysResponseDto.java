package racingcar.dtos.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import racingcar.domain.RacingCar;
import lombok.Getter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaysResponseDto {
    private String winners;
    private List<RacingCar> racingCars;
}
