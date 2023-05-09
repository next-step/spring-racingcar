package racingcar.game.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.game.domain.PlayResult;
import racingcar.game.domain.PlayResultRepository;
import racingcar.game.domain.PlayerHistory;
import racingcar.game.domain.PlayerHistoryEntity;
import racingcar.game.domain.PlayerHistoryRepository;
import racingcar.game.domain.RacingGame;
import racingcar.game.domain.RandomMoveStrategy;
import racingcar.game.application.dto.GameResult;
import racingcar.game.application.dto.GameStartData;

@Service
public class GameService {

    private final PlayResultRepository playResultRepository;
    private final PlayerHistoryRepository playerHistoryRepository;

    public GameService(PlayResultRepository playResultRepository, PlayerHistoryRepository playerHistoryRepository) {
        this.playResultRepository = playResultRepository;
        this.playerHistoryRepository = playerHistoryRepository;
    }

    @Transactional
    public GameResult play(GameStartData gameStartData) {
        RacingGame racingGame = gameStartData.toRacingGame();
        racingGame.playRound(gameStartData.getCount(), new RandomMoveStrategy());
        Long savedId = playResultRepository.save(PlayResult.of(gameStartData.getCount()));
        playerHistoryRepository.saveAll(converterPlayerHistories(savedId, racingGame));
        return GameResult.from(racingGame);
    }

    private List<PlayerHistory> converterPlayerHistories(Long playResultId, RacingGame racingGame) {
        return racingGame.getRacingCars()
            .stream()
            .map(racingCar -> PlayerHistory.of(playResultId, racingCar, racingGame.isWinner(racingCar.getName())))
            .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public List<GameResult> loadGameHistory() {
        Map<Long, List<PlayerHistoryEntity>> groupedPlayerHistoryEntitiesByPlayResultId = playerHistoryRepository.findAll()
            .stream()
            .collect(Collectors.groupingBy(PlayerHistoryEntity::getPlayResultId));
        return converterPlayResultResponses(groupedPlayerHistoryEntitiesByPlayResultId);
    }

    private List<GameResult> converterPlayResultResponses(Map<Long, List<PlayerHistoryEntity>> groupedPlayerHistoryEntitiesByPlayResultId) {
        return playResultRepository.findAll()
            .stream()
            .map(playResultEntity -> GameResult.from(playResultEntity, groupedPlayerHistoryEntitiesByPlayResultId))
            .collect(Collectors.toUnmodifiableList());
    }
}
