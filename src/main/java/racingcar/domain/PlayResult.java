package racingcar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import racingcar.domain.dto.RacingGameResult;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayResult {
    private int id;
    private int trialCount;
    private RacingGameResult racingGameResult;
    private LocalDateTime createdAt;

}
