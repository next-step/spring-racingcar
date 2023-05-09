package racingcar.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GameHistory {
  private int gameId;
  private String winners;
  private String name;
  private int position;
}
