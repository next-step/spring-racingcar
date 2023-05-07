package racingcar.domain;

import racingcar.domain.dto.PlayResultDto;
import racingcar.domain.strategy.MovingStrategy;

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

    public static List<PlayResultDto> findWinners(List<PlayResultDto> playResultDtos) {
        return findWinners(playResultDtos, getMaxPosition(playResultDtos));
    }

    private static List<PlayResultDto> findWinners(List<PlayResultDto> playResultDtos, Position maxPosition) {
        return playResultDtos.stream()
                .filter(playResult -> playResult.isPositionEquals(maxPosition))
                .collect(Collectors.toUnmodifiableList());
    }

    private static Position getMaxPosition(List<PlayResultDto> playResultDtos) {
        return playResultDtos.stream().max(Comparator.comparing(PlayResultDto::getPosition))
                .map(PlayResultDto::getPosition)
                .orElseThrow(() -> new IllegalArgumentException("PlayResult가 존재하지 않습니다."));
    }

    public void play(MovingStrategy movingStrategy) {
        playCount--;
        cars.move(movingStrategy);
    }

    public List<PlayResultDto> getPlayResults() {
        return cars.getPlayResults();
    }

    public boolean isEnd() {
        return playCount <= 0;
    }
}
