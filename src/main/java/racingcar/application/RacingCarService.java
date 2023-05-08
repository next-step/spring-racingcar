package racingcar.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.MovementPolicy;
import racingcar.domain.Names;
import racingcar.domain.RacingCars;
import racingcar.domain.repository.RacingCarRepository;
import racingcar.dto.RacingCarNamePosition;
import racingcar.dto.RacingCarPlayResponse;
import racingcar.dto.RacingCarRoundResult;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacingCarService {

  private final RacingCarRepository racingCarRepository;

  @Transactional
  public RacingCarPlayResponse play(String names, int count) {
    // 초기 게임 히스토리 데이터 저장
    Integer gameId = this.racingCarRepository.saveGameHistory(names, count);

    // 차량 인스턴스 생성
    RacingCars cars = new RacingCars(new Names(names));

    // 배치 쿼리를 위한 데이터 리스트 생성
    List<RacingCarRoundResult> roundResults = new LinkedList<>();

    // 게임 라운드 진행
    MovementPolicy movementPolicy = new MovementPolicy();
    for (int i = 1; i <= count; i++) {
      cars.playEachRound(movementPolicy);
      roundResults.addAll(createRoundResults(gameId, i, cars));
    }

    // 게임 라운드 히스토리 데이터 저장
    this.racingCarRepository.saveRoundHistory(roundResults);

    // 우승자 조회
    String winners = cars.getWinners();

    // 우승자 데이터 업데이트
    this.racingCarRepository.updateWinners(gameId, winners);

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

  private List<RacingCarRoundResult> createRoundResults(
      Integer gameId, int count, RacingCars cars) {
    return cars.getValues().stream()
        .map(
            car ->
                RacingCarRoundResult.builder()
                    .gameId(gameId)
                    .round(count)
                    .carName(car.getName())
                    .position(car.getPosition())
                    .build())
        .collect(Collectors.toList());
  }
}
