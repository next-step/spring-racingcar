package racingcar.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import racingcar.domain.RacingCar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RacingResultDto {

  private String winners;
  private List<RacingCar> racingCars;
}