package racingcar.domain.service;

import org.springframework.stereotype.Service;
import racingcar.domain.entity.RacingCars;

@Service
public class RacingCarService {

    public RacingCars playRounds(RacingCars racingCars, int count) {
        for (int i = 0; i < count; i++) {
            racingCars.playRound();
        }
        return racingCars;
    }
}
