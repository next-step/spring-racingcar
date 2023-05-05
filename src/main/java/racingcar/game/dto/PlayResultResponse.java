package racingcar.game.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.domain.PlayResult;
import racingcar.game.domain.PlayerHistory;
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

    public static PlayResultResponse from(PlayResult playResults,
        Map<Long, List<PlayerHistory>> groupedPlayerHistoriesByPlayResultId) {
        List<PlayerHistory> playerHistories = groupedPlayerHistoriesByPlayResultId.get(playResults.getId());

        String winners = playerHistories.stream()
            .filter(PlayerHistory::getIs_winner)
            .map(PlayerHistory::getName)
            .collect(Collectors.joining(JOIN_DELIMITER));

        return new PlayResultResponse(winners,
            playerHistories.stream()
                .map(RacingCarResponse::from)
                .collect(Collectors.toUnmodifiableList()));
    }
}
