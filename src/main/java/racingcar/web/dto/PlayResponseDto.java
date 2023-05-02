package racingcar.web.dto;

import java.util.List;

public class PlayResponseDto {

    private String winners;
    private List<RacingCar> racingCars;

    public PlayResponseDto(String winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public static class RacingCar {

        private String name;
        private int position;

        public RacingCar(String name, int position) {
            this.name = name;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

    }

}
