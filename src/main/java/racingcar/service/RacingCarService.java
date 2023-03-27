package racingcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.model.RacingResponse;
import racingcar.repository.CarRepository;
import racingcar.repository.PlayResultRepository;

@Service
@RequiredArgsConstructor
public class RacingCarService {
    private final CarRepository carRepository;

    private final PlayResultRepository playResultRepository;

    @Transactional
    public RacingResponse startRacing(String names, int targetDistance) {
        PlayResult playResult = new PlayResult();
        Cars cars = new Cars(carRepository);
        cars.makeCars(playResult, names);
        cars.moveCars(targetDistance);

        playResult.setWinners(cars.getWinnerNames());
        playResultRepository.save(playResult);
        cars.save();
        return new RacingResponse(cars.getWinnerNames(), cars.getCars());
    }

    @Transactional
    public List<RacingResponse> getRacingHistory() {
        List<RacingResponse> racingHistory = new ArrayList<>();

        playResultRepository.findAll().stream()
                .map(playResult -> new RacingResponse(playResult.getWinners(), playResult.getCar()))
                .forEach(racingResponse -> racingHistory.add(racingResponse));

        return racingHistory;
    }
}
