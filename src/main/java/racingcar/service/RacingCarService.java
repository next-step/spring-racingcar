package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingStartDto;
import racingcar.jdbctemplate.InsertDao;
import racingcar.reponse.RacingResultResponse;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class RacingCarService {
    public static RacingResultResponse playRacingGame(RacingStartDto racingStartDto) {

        Cars cars = new Cars(Arrays.stream(racingStartDto.getNames().split(","))
                .map(it -> new Car(it.trim()))
                .collect(Collectors.toList()));

        RacingCar racingCar = new RacingCar(cars, racingStartDto.getTrial());
        while (!racingCar.isEnd()) {
            racingCar.run();
        }
        Cars winners = racingCar.getWinner();
        cars.getCars().forEach(it ->
                InsertDao.insertWithMap(it, racingStartDto.getTrial(), winners.getCarNames())
        );

        return new RacingResultResponse(
                winners.getCarNames(),
                cars
        );
    }
}
