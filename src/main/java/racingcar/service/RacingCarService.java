package racingcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.model.RacingResponse;
import racingcar.repository.CarsRepository;
import racingcar.repository.PlayResultWrapperRepository;

@RequiredArgsConstructor
public class RacingCarService {
    private final CarsRepository carsRepository;
    // private final JpaRepository<PlayResult, Long> playResultRepository;
    private final PlayResultWrapperRepository playResultWarpperRepository;

    @Transactional
    public RacingResponse startRacing(String names, int targetDistance) {
        PlayResult playResult = new PlayResult();
        Cars cars = Cars.makeCars(playResult, names);
        cars.moveCars(targetDistance);

        playResult.setWinners(cars.getWinnerNames());
        playResultWarpperRepository.save(playResult);
        carsRepository.save(cars);
        return new RacingResponse(cars.getWinnerNames(), cars.getCars());
    }

    @Transactional
    public List<RacingResponse> getRacingHistory() {
        List<RacingResponse> racingHistory = new ArrayList<>();

        playResultWarpperRepository.findAll().stream()
                .map(playResult -> new RacingResponse(playResult.getWinners(), playResult.getCar()))
                .forEach(racingResponse -> racingHistory.add(racingResponse));

        return racingHistory;
    }
}
