package racingcar.web.service;

import org.springframework.stereotype.Service;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCarGame;
import racingcar.strategy.MovingStrategy;
import racingcar.strategy.MovingStrategyType;
import racingcar.web.dao.PlayResultDao;
import racingcar.web.dao.PlayResultDetailDao;
import racingcar.web.entity.PlayResultDetail;

import java.util.List;
import java.util.stream.Collectors;

import static racingcar.strategy.MovingStrategyType.RANDOM;

@Service
public class PlayService {

    private static final String CAR_NAME_SEPARATOR = ",";
    private static final MovingStrategy MOVING_STRATEGY = MovingStrategyType.getStrategy(RANDOM);

    private final PlayResultDao playResultDao;
    private final PlayResultDetailDao playResultDetailDao;

    public PlayService(PlayResultDao playResultDao, PlayResultDetailDao playResultDetailDao) {
        this.playResultDao = playResultDao;
        this.playResultDetailDao = playResultDetailDao;
    }

    public List<PlayResult> play(String carNames, int playCount) {
        RacingCarGame racingCarGame = createRacingCarGame(carNames, playCount);
        List<PlayResult> playResults = null;

        while (!racingCarGame.isEnd()) {
            racingCarGame.play(MOVING_STRATEGY);
            playResults = racingCarGame.getPlayResults();
        }

        savePlayResults(playResults, playCount);

        return playResults;
    }

    public String findWinners(List<PlayResult> playResults) {
        return RacingCarGame.findWinners(playResults).stream()
                .map(PlayResult::getNameValue)
                .collect(Collectors.joining(CAR_NAME_SEPARATOR));
    }

    private RacingCarGame createRacingCarGame(String carNames, int playCount) {
        return new RacingCarGame(carNames.split(CAR_NAME_SEPARATOR), playCount);
    }

    private void savePlayResults(List<PlayResult> playResults, int playCount) {
        Long playResultId = playResultDao.insert(new racingcar.web.entity.PlayResult(playCount, findWinners(playResults)));

        List<PlayResultDetail> playResultDetails = playResults.stream()
                .map(playResult -> new PlayResultDetail(playResultId, playResult.getNameValue(), playResult.getPositionValue()))
                .collect(Collectors.toList());

        for (PlayResultDetail playResultDetail : playResultDetails) {
            playResultDetailDao.insert(playResultDetail);
        }
    }

}
