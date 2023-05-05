package racingcar.game.dto;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import racingcar.game.domain.RacingGame;
import racingcar.game.domain.RacingCar;

@RequiredArgsConstructor
@Getter
public class PlayRequest {

    @NotBlank(message = "자동차 이름은 필수 값 입니다.")
    @Length(min = 1, max = 50, message = "자동차 이름은 ,로 구분하고 최소 {min}자 ~ 최대 {max}자 입니다.")
    private final String names;

    @NotNull(message = "플레이 횟수는 필수 값입니디.")
    @Min(value = 1, message = "플래이 횟수는 최소 {value}보다 커야 합니다.")
    private final Integer count;

    public RacingGame toRacingGame() {
        return new RacingGame(Arrays.stream(names.split(","))
            .map(RacingCar::new)
            .collect(Collectors.toUnmodifiableList()));
    }
}
