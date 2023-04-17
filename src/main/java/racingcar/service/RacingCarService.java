package racingcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingResultResponse;
import racingcar.dto.RacingStartDto;
import racingcar.jdbctemplate.InsertDao;
import racingcar.jdbctemplate.QueryDao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacingCarService {
    private final InsertDao insertDao;
    private final QueryDao queryDao;

    public RacingResultResponse playRacingGame(RacingStartDto racingStartDto) {
        Cars cars = new Cars(Arrays.stream(racingStartDto.getNames().split(","))
                .map(it -> new Car(it.trim()))
                .collect(Collectors.toList()));

        RacingCar racingCar = new RacingCar(cars, racingStartDto.getTrial(), queryDao.getNextRound());
        while (!racingCar.isEnd()) {
            racingCar.run();
        }
        Cars winners = racingCar.getWinner();
        insertDao.insertRacingHistory(racingCar, racingStartDto.getTrial());
        insertDao.insertWinnerHistory(racingCar);

        return new RacingResultResponse(
                winners.getCarNames(),
                cars
        );
    }

    public List<RacingResultResponse> getRacingGame() {
        return queryDao.getPlayHistory();
    }
}
