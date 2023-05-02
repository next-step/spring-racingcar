package racingcar.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RacingCarNamePosition {
  private String name;
  private int position;
}
