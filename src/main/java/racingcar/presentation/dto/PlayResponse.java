package racingcar.presentation.dto;

import racingcar.domain.PlayHistory;
import racingcar.domain.PlayResult;
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
        String winner = String.join(",", winners);
        return of(winner, racingCars);
    }

    public static PlayResponse of(String winners, RacingCars racingCars) {
        List<RacingCarDto> carDtos = racingCars.getValue()
                .stream()
                .map(RacingCarDto::new)
                .collect(Collectors.toList());

        return new PlayResponse(winners, carDtos);
    }

    public static PlayResponse of(PlayResult playResult, List<PlayHistory> histories) {
        String winners = playResult.getWinners();
        List<RacingCarDto> racingCarDtos = histories.stream()
                .map(RacingCarDto::new)
                .collect(Collectors.toList());
        return new PlayResponse(winners, racingCarDtos);
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

        public RacingCarDto(PlayHistory playHistory) {
            this.name = playHistory.getName();
            this.position = playHistory.getPosition();
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    }

}
