package racingcar.domain.service;

import org.springframework.stereotype.Service;
import racingcar.domain.RacingCars;
import racingcar.domain.dto.RacingGameResult;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class RacingCarService {

    public RacingGameResult plays(String names, Integer count) {
        RacingCars racingCars = new RacingCars(names);

        // 경주 시작
        for (int i = 0; i < count; i++) {
            racingCars.playRound();
        }

        // 우승자 반환
        return new RacingGameResult(
                racingCars.findWinners(), racingCars.getRacingCarDtos());
    }

}
