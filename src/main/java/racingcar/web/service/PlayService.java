package racingcar.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCarGame;
import racingcar.strategy.MovingStrategy;
import racingcar.strategy.MovingStrategyType;
import racingcar.web.dao.PlayHistoryDao;
import racingcar.web.dao.PlayHistoryDetailDao;
import racingcar.web.entity.PlayHistory;
import racingcar.web.entity.PlayHistoryDetail;

import java.util.List;
import java.util.stream.Collectors;

import static racingcar.strategy.MovingStrategyType.RANDOM;

@Service
public class PlayService {

    private static final String CAR_NAME_SEPARATOR = ",";
    private static final MovingStrategy MOVING_STRATEGY = MovingStrategyType.getStrategy(RANDOM);

    private final PlayHistoryDao playHistoryDao;
    private final PlayHistoryDetailDao playHistoryDetailDao;

    public PlayService(PlayHistoryDao playHistoryDao, PlayHistoryDetailDao playHistoryDetailDao) {
        this.playHistoryDao = playHistoryDao;
        this.playHistoryDetailDao = playHistoryDetailDao;
    }

    public List<PlayResult> play(String carNames, int playCount) {
        RacingCarGame racingCarGame = createRacingCarGame(carNames, playCount);
        List<PlayResult> playResults = null;

        while (!racingCarGame.isEnd()) {
            racingCarGame.play(MOVING_STRATEGY);
            playResults = racingCarGame.getPlayResults();
        }

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

    @Transactional
    public Long savePlayResults(List<PlayResult> playResults, int playCount) {
        PlayHistory playHistory = new PlayHistory(playCount, findWinners(playResults));
        Long playHistoryId = playHistoryDao.insert(playHistory);

        List<PlayHistoryDetail> playHistoryDetails = playResults.stream()
                .map(playResult -> new PlayHistoryDetail(playHistoryId, playResult.getNameValue(), playResult.getPositionValue()))
                .collect(Collectors.toList());

        for (PlayHistoryDetail playHistoryDetail : playHistoryDetails) {
            playHistoryDetailDao.insert(playHistoryDetail);
        }

        return playHistoryId;
    }

}
