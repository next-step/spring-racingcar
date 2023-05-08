package racingcar.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.constant.GameEnvironment;
import racingcar.domain.MovementPolicy;
import racingcar.domain.Names;
import racingcar.domain.RacingCars;
import racingcar.repository.GameHistoryRepository;
import racingcar.repository.RoundHistoryRepository;
import racingcar.dto.RacingCarNamePosition;
import racingcar.dto.RacingCarPlayResponse;
import racingcar.dto.RacingCarRoundResult;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacingCarService {

  private final GameHistoryRepository gameHistoryRepository;
  private final RoundHistoryRepository roundHistoryRepository;

  @Transactional
  public RacingCarPlayResponse play(String names, int count) {
    RacingCars cars = new RacingCars(new Names(names, GameEnvironment.CAR_NAME_DELIMITER));

    List<RacingCarRoundResult> roundResults = new LinkedList<>();
    MovementPolicy movementPolicy = new MovementPolicy();
    for (int i = 1; i <= count; i++) {
      cars.playEachRound(movementPolicy);
      roundResults.addAll(createRoundResults(i, cars));
    }

    String winners = cars.getWinners();
    Integer gameId = this.gameHistoryRepository.save(names, count);
    this.roundHistoryRepository.save(gameId, roundResults);
    this.gameHistoryRepository.updateWinners(gameId, winners);

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

  private List<RacingCarRoundResult> createRoundResults(int count, RacingCars cars) {
    return cars.getValues().stream()
        .map(
            car ->
                RacingCarRoundResult.builder()
                    .round(count)
                    .carName(car.getName())
                    .position(car.getPosition())
                    .build())
        .collect(Collectors.toList());
  }
}
