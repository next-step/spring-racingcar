package racingcar.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Getter
@ToString
@Builder
public class PlayResult {
    private String winners;
    private int trialCount;
    private List<RacingCar> racingCars;
    private LocalDateTime createdAt;

    public PlayResult(String winners, int trialCount, List<RacingCar> racingCars, LocalDateTime createdAt) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.racingCars = racingCars;
        this.createdAt = createdAt;
    }

    public PlayResult(String winners, int trialCount, List<RacingCar> racingCars) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.racingCars = racingCars;
    }
}
