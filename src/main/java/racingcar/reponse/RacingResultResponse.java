package racingcar.reponse;

import lombok.Getter;
import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.List;

@Getter
public class RacingResultResponse {
    private final String winners;
    private final List<Car> racingCars;

    public RacingResultResponse(String winners, Cars cars) {
        this.winners = winners;
        this.racingCars = cars.getCars();
    }
 }
