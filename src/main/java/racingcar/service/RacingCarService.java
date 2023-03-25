package racingcar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.model.RacingRequest;
import racingcar.model.RacingResponse;
import racingcar.repository.PlayResultRepository;
import racingcar.utils.RacingCarUtils;

@Service
public class RacingCarService {

    @Autowired
    private Cars cars;
    @Autowired
    private PlayResultRepository playResultRepository;

    public RacingCarService() {
    }

    public RacingCarService(Cars cars, PlayResultRepository playResultRepository) {
        this.cars = cars;
        this.playResultRepository = playResultRepository;
    }

    @Transactional
    public RacingResponse startRacing(RacingRequest racingRequest) {
        PlayResult playResult = new PlayResult();
        cars.makeCars(playResult, RacingCarUtils.stringToList(racingRequest.getNames()));
        cars.moveCars(racingRequest.getCount());
        cars.save();

        playResult.setWinners(cars.getWinnerNames());
        playResultRepository.save(playResult);
        return new RacingResponse(cars.getWinnerNames(), cars.getCars());
    }

    public void startRacing(List<String> carNames, int targetDistance) {
        Cars cars = new Cars(null);
        PlayResult playResult = new PlayResult();
        cars.makeCars(playResult, carNames);
        cars.moveCars(targetDistance);

        playResult.setWinners(cars.getWinnerNames());
        cars.printResult();
    }
}
