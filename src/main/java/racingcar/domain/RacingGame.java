package racingcar.domain;

import java.util.List;

public class RacingGame {

    private RacingCars racingCars;

    private RacingGame(RacingCars racingCars) {
        this.racingCars = racingCars;
    }

    public static RacingGame of(RacingCars racingCars) {
        return new RacingGame(racingCars);
    }

    public void play(int playRound, MoveStrategy moveStrategy) {
        for (int i = 0; i < playRound; i++) {
            racingCars.move(moveStrategy);
        }
    }

    public String getWinners() {
        List<String> carNames = racingCars.getMaxDistanceCarName();
        return String.join(",", carNames);
    }

}
