package racingcar.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RacingCarRoundResult {
  private int gameId;
  private int round;
  private String carName;
  private int position;
}
