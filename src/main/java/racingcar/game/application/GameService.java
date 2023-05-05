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
import racingcar.game.dto.PlayRequest;
import racingcar.game.dto.PlayResultResponse;

@Service
public class GameService {

    private final PlayResultRepository playResultRepository;
    private final PlayerHistoryRepository playerHistoryRepository;

    public GameService(PlayResultRepository playResultRepository, PlayerHistoryRepository playerHistoryRepository) {
        this.playResultRepository = playResultRepository;
        this.playerHistoryRepository = playerHistoryRepository;
    }

    @Transactional
    public PlayResultResponse play(PlayRequest playRequest) {
        RacingGame racingGame = playRequest.toRacingGame();
        racingGame.playRound(playRequest.getCount(), new RandomMoveStrategy());

        Long savedId = playResultRepository.save(PlayResult.of(playRequest.getCount()));
        playerHistoryRepository.saveAll(converterPlayerHistories(savedId, racingGame));
        return PlayResultResponse.from(racingGame.getWinners(), racingGame.getRacingCars());
    }

    private List<PlayerHistory> converterPlayerHistories(Long playResultId, RacingGame racingGame) {
        return racingGame.getRacingCars()
            .stream()
            .map(racingCar -> PlayerHistory.of(playResultId, racingCar, racingGame.isWinner(racingCar.getName())))
            .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public List<PlayResultResponse> loadGameHistory() {
        Map<Long, List<PlayerHistoryEntity>> groupedPlayerHistoryEntitiesByPlayResultId = playerHistoryRepository.findAll()
            .stream()
            .collect(Collectors.groupingBy(PlayerHistoryEntity::getPlayResultId));
        return converterPlayResultResponses(groupedPlayerHistoryEntitiesByPlayResultId);
    }

    private List<PlayResultResponse> converterPlayResultResponses(Map<Long, List<PlayerHistoryEntity>> groupedPlayerHistoryEntitiesByPlayResultId) {
        return playResultRepository.findAll()
            .stream()
            .map(playResultEntity -> PlayResultResponse.from(playResultEntity, groupedPlayerHistoryEntitiesByPlayResultId))
            .collect(Collectors.toUnmodifiableList());
    }
}
