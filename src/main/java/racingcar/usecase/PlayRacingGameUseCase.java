package racingcar.usecase;

import racingcar.usecase.request.PlayRacingGameRequest;
import racingcar.usecase.response.PlayRacingGameResponse;

import javax.validation.Valid;

public interface PlayRacingGameUseCase {
    PlayRacingGameResponse playRacingGame(@Valid PlayRacingGameRequest request);
}
