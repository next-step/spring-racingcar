package racingcar.dto;

import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RacingCarPlayResponse {
  private String winners;
  private List<RacingCarNamePosition> racingCars;

  public static RacingCarPlayResponse of(List<GameHistory> gameHistories) {
    RacingCarPlayResponse response = new RacingCarPlayResponse();
    response.winners = gameHistories.stream().findFirst().get().getWinners();
    response.racingCars =
        gameHistories.stream()
            .map(
                gameHistory ->
                    RacingCarNamePosition.builder()
                        .name(gameHistory.getName())
                        .position(gameHistory.getPosition())
                        .build())
            .collect(Collectors.toList());

    return response;
  }
}
