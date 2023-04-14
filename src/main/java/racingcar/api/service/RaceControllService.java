package racingcar.api.service;

import org.springframework.stereotype.Service;
import racingcar.api.response.CarRacingResponse;
import racingcar.api.response.Car;
import racingcar.racing.Driver;
import racingcar.racing.RaceControll;
import racingcar.racing.RacingCar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceControllService {

    public CarRacingResponse race(String drivers, int round) {

        RaceControll raceControll = new RaceControll(drivers);
        raceControll.raceMultiRound(round);

        String winners = raceControll.getWinners()
                .stream().map(Driver::getName)
                .collect(Collectors.joining(", "));

        List<RacingCar> finishingCars = raceControll.getCars();
        Collections.sort(finishingCars);
        List<Car> cars = new ArrayList<>();
        finishingCars.stream()
                .forEach(
                (RacingCar racingCar) -> {
                    cars.add(new Car(racingCar));
                }
        );

        return new CarRacingResponse(winners, cars);
    }


}
