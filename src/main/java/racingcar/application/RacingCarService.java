package racingcar.application;

import org.springframework.stereotype.Service;
import racingcar.domain.MovementPolicy;
import racingcar.domain.RacingCars;
import racingcar.dto.RacingCarNamePosition;
import racingcar.dto.RacingCarPlayResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {
  public RacingCarPlayResponse play(RacingCars cars, int count) {
    // 게임 라운드 진행
    MovementPolicy movementPolicy = new MovementPolicy();
    for (int i = 0; i < count; i++) {
      playEachRound(cars, movementPolicy);
    }

    // 우승자 조회
    String winners = cars.getWinners();

    // 모든 차에 대한 경기 결과
    List<RacingCarNamePosition> racingCarNamePositions =
        cars.getValues().stream()
            .map(
                car ->
                    RacingCarNamePosition.builder()
                        .name(car.getName())
                        .position(car.getPosition())
                        .build())
            .collect(Collectors.toList());

    return RacingCarPlayResponse.builder()
        .winners(winners)
        .racingCars(racingCarNamePositions)
        .build();
  }

  private void playEachRound(RacingCars cars, MovementPolicy movementPolicy) {
    cars.getValues().forEach(car -> car.move(movementPolicy.action()));
  }
}
