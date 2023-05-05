package racingcar.game.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.game.dao.PlayResultDao;
import racingcar.game.dao.PlayerHistoryDao;
import racingcar.game.domain.PlayerHistory;
import racingcar.game.domain.RacingGame;
import racingcar.game.domain.PlayResult;
import racingcar.game.domain.RandomMoveStrategy;
import racingcar.game.dto.PlayRequest;
import racingcar.game.dto.PlayResultResponse;

@Service
public class GameService {

    private final PlayResultDao playResultDao;
    private final PlayerHistoryDao playerHistoryDao;

    public GameService(PlayResultDao playResultDao, PlayerHistoryDao playerHistoryDao) {
        this.playResultDao = playResultDao;
        this.playerHistoryDao = playerHistoryDao;
    }

    @Transactional
    public PlayResultResponse play(PlayRequest playRequest) {
        RacingGame racingGame = playRequest.toRacingGame();
        racingGame.playRound(playRequest.getCount(), new RandomMoveStrategy());

        Long savedId = playResultDao.save(PlayResult.of(playRequest.getCount()));
        playerHistoryDao.saveAll(converterPlayers(savedId, racingGame));
        return PlayResultResponse.from(racingGame.getWinners(), racingGame.getRacingCars());
    }

    private List<PlayerHistory> converterPlayers(Long playResultId, RacingGame racingGame) {
        return racingGame.getRacingCars()
            .stream()
            .map(racingCar -> PlayerHistory.of(playResultId, racingCar, racingGame.isWinner(racingCar.getName())))
            .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public List<PlayResultResponse> loadGameHistory() {
        return converterPlayResultResponses(groupedPlayerHistoriesByPlayResultId());
    }

    private Map<Long, List<PlayerHistory>> groupedPlayerHistoriesByPlayResultId() {
        return playerHistoryDao.findAll()
            .stream()
            .collect(Collectors.groupingBy(PlayerHistory::getPlayResultId));
    }

    private List<PlayResultResponse> converterPlayResultResponses(Map<Long, List<PlayerHistory>> groupedPlayerHistoriesByPlayResultId) {
        return playResultDao.findAll()
            .stream()
            .map(playResult -> PlayResultResponse.from(playResult, groupedPlayerHistoriesByPlayResultId))
            .collect(Collectors.toUnmodifiableList());
    }
}
