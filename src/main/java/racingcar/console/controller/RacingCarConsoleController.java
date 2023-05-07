package racingcar.console.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import racingcar.console.view.InputView;
import racingcar.console.view.ResultView;
import racingcar.domain.dto.PlayResultDto;
import racingcar.service.PlayService;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RacingCarConsoleController {

    private static final String CAR_NAME_SEPARATOR = ", ";

    private final PlayService playService;

    public void play() {
        String[] carNames = InputView.getCarNames();
        int playCount = InputView.getPlayCount();

        List<PlayResultDto> playResultDtos = playService.play(carNames, playCount);
        String winners = joinNames(playService.findWinners(playResultDtos));

        playService.savePlayResults(playCount, winners, playResultDtos);

        ResultView.printWinners(winners);
        ResultView.printPlayResults(playResultDtos);
    }

    private String joinNames(String[] carNames) {
        return String.join(CAR_NAME_SEPARATOR, carNames);
    }

}
