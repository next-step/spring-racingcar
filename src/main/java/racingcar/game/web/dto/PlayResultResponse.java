package racingcar.game.web.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.application.dto.GameResult;

@RequiredArgsConstructor
@Getter
public class PlayResultResponse {

    private static final String JOIN_DELIMITER = ",";

    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public static PlayResultResponse from(GameResult gameResult) {
        return new PlayResultResponse(gameResult.getWinners(), RacingCarResponse.from(gameResult.getPlayers()));
    }

    public static List<PlayResultResponse> from(List<GameResult> gameResults) {
        return gameResults.stream()
            .map(PlayResultResponse::from)
            .collect(Collectors.toUnmodifiableList());
    }
}
