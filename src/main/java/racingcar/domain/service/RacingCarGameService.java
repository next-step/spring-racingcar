package racingcar.domain.service;

import lombok.RequiredArgsConstructor;
import racingcar.domain.RacingCarGame;
import racingcar.domain.dto.PlayResultDto;
import racingcar.domain.strategy.MovingStrategy;

import java.util.List;

@RequiredArgsConstructor
public class RacingCarGameService {

    private final MovingStrategy movingStrategy;

    public List<PlayResultDto> play(String[] carNames, int playCount) {
        RacingCarGame racingCarGame = new RacingCarGame(carNames, playCount);
        List<PlayResultDto> playResultDtos = null;

        while (!racingCarGame.isEnd()) {
            racingCarGame.play(movingStrategy);
            playResultDtos = racingCarGame.getPlayResults();
        }

        return playResultDtos;
    }

    public String[] findWinners(List<PlayResultDto> playResultDtos) {
        return RacingCarGame.findWinners(playResultDtos).stream()
                .map(PlayResultDto::getNameValue)
                .toArray(String[]::new);
    }

}
