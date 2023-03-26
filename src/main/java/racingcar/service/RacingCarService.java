package racingcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

    @Transactional
    public List<RacingResponse> getRacingHistory() {
        List<RacingResponse> racingHistory = new ArrayList<>();

        playResultRepository.findAll().forEach(playResult -> {
            RacingResponse response = new RacingResponse(playResult.getWinners(), playResult.getCar());
            racingHistory.add(response);
        });

        return racingHistory;
    }
}
