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
    private int id;
    private int trialCount;
    private List<String> winners;
    private RacingCars racingCars;
    private LocalDateTime createdAt;

    public String getWinnersAsString() {
        return String.join(",", winners);
    }

    public void setWinnersFromString(String str) {
        this.winners = Arrays.stream(str.split(","))
                .collect(Collectors.toList());
    }

    public void setRacingCars(RacingCars racingCars) {
        this.racingCars = racingCars;
    }

}
