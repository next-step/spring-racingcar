package racingcar.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

public class PlayResult {
    private int id;
    private String winners;
    private int trialCount;
    private List<RacingCar> racingCars;
    private LocalDateTime createdAt;

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public PlayResult() {
    }

    public PlayResult(String winners, int trialCount, List<RacingCar> racingCars) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.racingCars = racingCars;
    }

    public PlayResult(int id, String winners, int trialCount, List<RacingCar> racingCars, LocalDateTime createdAt) {
        this.id = id;
        this.winners = winners;
        this.trialCount = trialCount;
        this.racingCars = racingCars;
        this.createdAt = createdAt;
    }

    public static PlayResult of(RacingCars racingCars, int count) {
        return new PlayResult(
                String.join(",", racingCars.getWinners()),
                count,
                racingCars.getRacingCars()
        );
    }
}
