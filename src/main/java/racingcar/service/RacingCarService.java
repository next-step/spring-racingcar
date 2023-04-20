package racingcar.service;

import org.springframework.stereotype.Component;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingStartDto;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class RacingCarService {
    public RacingCar playRacingGame(RacingStartDto racingStartDto, int round) {
        Cars cars = new Cars(Arrays.stream(racingStartDto.getNames().split(","))
                .map(it -> new Car(it.trim()))
                .collect(Collectors.toList()));

        RacingCar racingCar = new RacingCar(cars, racingStartDto.getTrial(), round);
        while (!racingCar.isEnd()) {
            racingCar.run();
        }

        return racingCar;
    }
}
