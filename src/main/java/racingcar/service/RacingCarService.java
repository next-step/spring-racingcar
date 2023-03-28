package racingcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.model.RacingResponse;
import racingcar.repository.CarsRepository;

@RequiredArgsConstructor
public class RacingCarService {
    private final CarsRepository carsRepository;
    private final JpaRepository<PlayResult, Long> playResultRepository;

    @Transactional
    public RacingResponse startRacing(String names, int targetDistance) {
        PlayResult playResult = new PlayResult();
        Cars cars = Cars.makeCars(playResult, names);
        cars.moveCars(targetDistance);

        playResult.setWinners(cars.getWinnerNames());
        playResultRepository.save(playResult);
        carsRepository.save(cars);
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
