package racingcar.domain;

import racingcar.strategy.MovingStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCarGame {

    private final Cars cars;
    private int playCount;

    public RacingCarGame(String[] carNames, int playCount) {
        this.cars = new Cars(carNames);
        this.playCount = validatePlayCount(playCount);
    }

    private int validatePlayCount(int playCount) {
        if (playCount <= 0) {
            throw new IllegalArgumentException("플레이 횟수는 1회 이상이어야 합니다.");
        }
        return playCount;
    }

    public static List<PlayResult> findWinners(List<PlayResult> playResults) {
        return findWinners(playResults, getMaxPosition(playResults));
    }

    private static List<PlayResult> findWinners(List<PlayResult> playResults, Position maxPosition) {
        return playResults.stream()
                .filter(playResult -> playResult.isPositionEquals(maxPosition))
                .collect(Collectors.toUnmodifiableList());
    }

    private static Position getMaxPosition(List<PlayResult> playResults) {
        return playResults.stream().max(Comparator.comparing(PlayResult::getPosition))
                .map(PlayResult::getPosition)
                .orElseThrow(() -> new IllegalArgumentException("PlayResult가 존재하지 않습니다."));
    }

    public void play(MovingStrategy movingStrategy) {
        playCount--;
        cars.move(movingStrategy);
    }

    public List<PlayResult> getPlayResults() {
        return cars.getPlayResults();
    }

    public boolean isEnd() {
        return playCount <= 0;
    }
}
