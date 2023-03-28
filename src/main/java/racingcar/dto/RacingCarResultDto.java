package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;


public class RacingCarResultDto {

    private String winners;
    private List<Car> racingCars;

    public RacingCarResultDto() {
    }

    public RacingCarResultDto(String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(List<Car> racingCars) {
        this.racingCars = racingCars;
    }
}
