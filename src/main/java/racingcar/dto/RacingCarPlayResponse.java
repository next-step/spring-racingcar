package racingcar.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RacingCarPlayResponse {
  private String winners;
  private List<RacingCarNamePosition> racingCars;
}
