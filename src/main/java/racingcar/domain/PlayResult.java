package racingcar.domain;

import lombok.Builder;
import lombok.Getter;
import racingcar.dto.RacingCarResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Getter
public class PlayResult {
    private final String winners;
    private final int trialCount;
    private final List<RacingCarResponse> racingCars;
    private final LocalDateTime createdAt;

    @Builder
    public PlayResult(String winners, int trialCount, List<RacingCarResponse> racingCars, LocalDateTime createdAt) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.racingCars = racingCars;
        this.createdAt = createdAt;
    }


}
