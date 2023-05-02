package racingcar.play.application.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.play.domain.RacingCars;

@RequiredArgsConstructor
@Getter
public class PlayResultResponse {

    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public static PlayResultResponse from(RacingCars racingCars) {
        return new PlayResultResponse(racingCars.getWinners(),
            racingCars.getRacingCars()
                .stream()
                .map(RacingCarResponse::from)
                .collect(Collectors.toUnmodifiableList()));
    }
}
