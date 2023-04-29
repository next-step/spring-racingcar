package racingcar.api.dto;

import racingcar.domain.entity.RacingCar;

import java.util.List;

public class RacingCarResponse {

    private List<String> winners;

    private List<RacingCar> racingCars;

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public RacingCarResponse(List<String> winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }
}
