package racingcar.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingCars;
import racingcar.domain.dto.RacingGameResultDto;
import racingcar.domain.repository.PlayResultRepository;
import racingcar.domain.repository.RacingCarRepository;

@Service
@RequiredArgsConstructor
public class RacingCarService {

    private final PlayResultRepository playResultRepository;
    private final RacingCarRepository racingCarRepository;

    @Transactional
    public RacingGameResultDto plays(String names, Integer count) {
        RacingCars racingCars = new RacingCars(names);

        // 경주 시작
        for (int i = 0; i < count; i++) {
            racingCars.playRound();
        }
        RacingGameResultDto racingGameResultDto = new RacingGameResultDto(
                racingCars.findWinners(), racingCars.getRacingCarDtos());

        // 결과 저장


        // 우승자 반환
        return racingGameResultDto;
    }
}
