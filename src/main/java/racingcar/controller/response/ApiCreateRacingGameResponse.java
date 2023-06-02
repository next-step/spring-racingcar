package racingcar.controller.response;

import java.util.List;

public class ApiCreateRacingGameResponse {

    String winners;
    List<PlayerResponse> racingCars;

    public ApiCreateRacingGameResponse(String winners, List<PlayerResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResponse> getRacingCars() {
        return racingCars;
    }

    public static class PlayerResponse {
        String name;
        Integer position;

        public PlayerResponse(String name, Integer position) {
            this.name = name;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public Integer getPosition() {
            return position;
        }
    }

}
