package racingcar.console.controller;

import racingcar.console.view.InputView;
import racingcar.console.view.ResultView;
import racingcar.domain.dto.PlayResultDto;
import racingcar.domain.service.RacingCarGameService;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.domain.strategy.MovingStrategyType;

import java.util.List;

import static racingcar.domain.strategy.MovingStrategyType.RANDOM;

public class RacingCarConsoleController {

    private static final MovingStrategy MOVING_STRATEGY = MovingStrategyType.getStrategy(RANDOM);

    private final RacingCarGameService racingCarGameService = new RacingCarGameService(MOVING_STRATEGY);

    public void play() {
        List<PlayResultDto> playResultDtos = racingCarGameService.play(InputView.getCarNames(), InputView.getPlayCount());

        ResultView.printWinners(racingCarGameService.findWinners(playResultDtos));
        ResultView.printPlayResults(playResultDtos);
    }

}
