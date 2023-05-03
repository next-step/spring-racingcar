package racingcar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayResult {
    private static final String DELIMITER_BETWEEN_WINNER = ",";
    private int id;
    private int trialCount;
    private List<String> winners;
    private RacingCars racingCars;
    private LocalDateTime createdAt;

    public String getWinnersAsString() {
        return String.join(DELIMITER_BETWEEN_WINNER, winners);
    }

    public void setWinners(List<String> winners) {
        this.winners = winners;
    }

    public void setRacingCars(RacingCars racingCars) {
        this.racingCars = racingCars;
    }
}
