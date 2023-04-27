package racingcar.presentation.dto;

import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResponse {

    private String winners;
    private List<RacingCarDto> racingCars;

    private PlayResponse() {
    }

    private PlayResponse(String winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlayResponse of(String winners, RacingCars racingCars) {
        List<RacingCarDto> carDtos = racingCars.getRacingCars()
                .stream()
                .map(it -> new RacingCarDto(it))
                .collect(Collectors.toList());

        return new PlayResponse(winners, carDtos);
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }

    private static class RacingCarDto {
        private String name;
        private int position;

        public RacingCarDto(RacingCar racingCar) {
            this.name = racingCar.getName();
            this.position = racingCar.getPosition();
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    }

}
