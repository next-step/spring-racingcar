package racingcar.presentation.dto;

import java.util.List;

public class GamePlayHistory {

    private String winners;
    private List<RacingCar> racingCars;

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public void setRacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public class RacingCar {

        private String name;
        private long position;

        public RacingCar(String name, long position) {
            this.name = name;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public long getPosition() {
            return position;
        }
    }
}
