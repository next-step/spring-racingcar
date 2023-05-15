package racingcar.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import racingcar.RacingCar;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PlayResult {

    private long playId;
    private int trialCount;
    private String winners;
    private List<RacingCar> racingCars;
    private LocalDateTime createdAt;

    public PlayResult(String winners) {
        this.winners = winners;
    }

    public PlayResult(int trialCount, String winners, List<RacingCar> racingCars, LocalDateTime createdAt) {
        this.trialCount = trialCount;
        this.winners = winners;
        this.racingCars = racingCars;
        this.createdAt = createdAt;
    }

    public static PlayResult of (int trialCount, String winners, List<RacingCar> racingCars, LocalDateTime createdAt) {
        return new PlayResult(trialCount, winners, racingCars, createdAt);
    }

}
