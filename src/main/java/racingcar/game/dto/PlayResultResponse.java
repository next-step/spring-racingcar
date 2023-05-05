package racingcar.game.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.domain.PlayResultEntity;
import racingcar.game.domain.PlayerHistoryEntity;
import racingcar.game.domain.RacingCar;

@RequiredArgsConstructor
@Getter
public class PlayResultResponse {

    private static final String JOIN_DELIMITER = ",";

    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public static PlayResultResponse from(List<String> winners, List<RacingCar> racingCars) {
        return new PlayResultResponse(String.join(JOIN_DELIMITER, winners),
            racingCars.stream()
                .map(RacingCarResponse::from)
                .collect(Collectors.toUnmodifiableList()));
    }

    public static PlayResultResponse from(PlayResultEntity playResultEntity,
        Map<Long, List<PlayerHistoryEntity>> groupedPlayerHistoriesByPlayResultId) {
        List<PlayerHistoryEntity> playerHistories = groupedPlayerHistoriesByPlayResultId.get(playResultEntity.getId());

        String winners = playerHistories.stream()
            .filter(PlayerHistoryEntity::isWinner)
            .map(PlayerHistoryEntity::getName)
            .collect(Collectors.joining(JOIN_DELIMITER));

        return new PlayResultResponse(winners,
            playerHistories.stream()
                .map(RacingCarResponse::from)
                .collect(Collectors.toUnmodifiableList()));
    }
}
