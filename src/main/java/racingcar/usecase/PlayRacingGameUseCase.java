package racingcar.usecase;

import org.springframework.validation.annotation.Validated;
import racingcar.service.request.PlayRacingGameRequest;
import racingcar.service.response.PlayRacingGameResponse;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public interface PlayRacingGameUseCase {
    PlayRacingGameResponse playRacingGame(@Valid PlayRacingGameRequest request);
}
