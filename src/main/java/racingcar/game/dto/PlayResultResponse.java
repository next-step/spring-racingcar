package racingcar.game.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.domain.RacingGame;

@RequiredArgsConstructor
@Getter
public class PlayResultResponse {

    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public static PlayResultResponse from(RacingGame racingGame) {
        return new PlayResultResponse(String.join(",", racingGame.getWinners()),
            racingGame.getRacingCars()
                .stream()
                .map(RacingCarResponse::from)
                .collect(Collectors.toUnmodifiableList()));
    }
}
