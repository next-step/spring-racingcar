package racingcar.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.model.RacingResponse;
import racingcar.repository.PlayResultRepository;

@Service
@RequiredArgsConstructor
public class RacingCarService {
    private final Cars cars;

    private final PlayResultRepository playResultRepository;

    public RacingResponse startRacing(String names, int targetDistance) {
        PlayResult playResult = new PlayResult();
        cars.makeCars(playResult, names);
        cars.moveCars(targetDistance);

        playResult.setWinners(cars.getWinnerNames());
        playResultRepository.save(playResult);
        cars.save();
        cars.printResult();
        return new RacingResponse(cars.getWinnerNames(), cars.getCars());
    }
}
