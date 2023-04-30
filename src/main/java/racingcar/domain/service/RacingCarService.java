package racingcar.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCars;
import racingcar.domain.dto.RacingGameResult;
import racingcar.domain.repository.PlayResultRepository;

@Service
@RequiredArgsConstructor
public class RacingCarService {

    private final PlayResultRepository playResultRepository;

    @Transactional
    public RacingGameResult plays(String names, Integer count) {
        RacingCars racingCars = new RacingCars(names);

        // 경주 시작
        for (int i = 0; i < count; i++) {
            racingCars.playRound();
        }
        RacingGameResult racingGameResult = new RacingGameResult(
                racingCars.findWinners(), racingCars.getRacingCarDtos());

        // 결과 저장
        playResultRepository.save(PlayResult.builder()
                .trialCount(count)
                .racingGameResult(racingGameResult)
                .build());

        // 우승자 반환
        return racingGameResult;
    }
}
