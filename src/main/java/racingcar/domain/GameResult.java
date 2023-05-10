package racingcar.domain;

import java.util.List;

public class GameResult {

    private final List<RacingCar> racingCars;
    private final int trialCount;
    private final String winner;

    public GameResult(List<RacingCar> racingCars, int trialCount, String winner) {
        this.racingCars = racingCars;
        this.trialCount = trialCount;
        this.winner = winner;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinner() {
        return winner;
    }

}
