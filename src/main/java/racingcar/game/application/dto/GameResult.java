package racingcar.game.application.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.domain.PlayResultEntity;
import racingcar.game.domain.PlayerHistoryEntity;
import racingcar.game.domain.RacingGame;

@RequiredArgsConstructor
@Getter
public class GameResult {

    private static final String JOIN_DELIMITER = ",";

    private final String winners;
    private final List<Player> players;

    public static GameResult from(RacingGame racingGame) {
        return new GameResult(String.join(JOIN_DELIMITER, racingGame.getWinners()),
            Player.from(racingGame.getRacingCars()));
    }

    public static GameResult from(PlayResultEntity playResultEntity,
        Map<Long, List<PlayerHistoryEntity>> groupedPlayerHistoriesByPlayResultId) {
        List<PlayerHistoryEntity> playerHistories = groupedPlayerHistoriesByPlayResultId.get(playResultEntity.getId());

        String winners = playerHistories.stream()
            .filter(PlayerHistoryEntity::isWinner)
            .map(PlayerHistoryEntity::getName)
            .collect(Collectors.joining(JOIN_DELIMITER));

        return new GameResult(winners,
            playerHistories.stream()
                .map(Player::from)
                .collect(Collectors.toUnmodifiableList()));
    }
}
