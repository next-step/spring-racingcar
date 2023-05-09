package racingcar.game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;

@Getter
public class RacingGame {

    private final List<RacingCar> racingCars;
    private final List<String> winners;

    public RacingGame(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
        this.winners = new ArrayList<>();
    }

    public void playRound(int count, MoveStrategy moveStrategy) {
        IntStream.range(0, count)
            .forEach(i -> moveRacingCars(moveStrategy));
        winners.addAll(findWinners());
    }

    private void moveRacingCars(MoveStrategy moveStrategy) {
        racingCars.forEach(racingCar -> racingCar.move(moveStrategy.move()));
    }

    private List<String> findWinners() {
        int maxPosition = calculateMaxPosition();
        return racingCars.stream()
            .filter(racingCar -> racingCar.getPosition() >= maxPosition)
            .map(RacingCar::getName)
            .collect(Collectors.toUnmodifiableList());
    }

    private int calculateMaxPosition() {
        return racingCars.stream()
            .mapToInt(RacingCar::getPosition)
            .max()
            .orElse(Integer.MIN_VALUE);
    }

    public boolean isWinner(String name) {
        return winners.contains(name);
    }
}
