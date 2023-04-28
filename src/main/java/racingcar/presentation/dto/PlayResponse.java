package racingcar.presentation.dto;

import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResponse {

    private final String winners;
    private final List<RacingCarDto> racingCars;

    private PlayResponse(String winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlayResponse of(List<String> winners, RacingCars racingCars) {
        List<RacingCarDto> carDtos = racingCars.getValue()
                .stream()
                .map(it -> new RacingCarDto(it))
                .collect(Collectors.toList());

        String winner = String.join(",", winners);
        return new PlayResponse(winner, carDtos);
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }

    private static class RacingCarDto {
        private final String name;
        private final int position;

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
