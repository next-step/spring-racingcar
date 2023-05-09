package racingcar.business;

import org.springframework.stereotype.Service;
import racingcar.business.domain.RacingCars;
import racingcar.data.PlayHistoryRepository;
import racingcar.data.PlayResultRepository;
import racingcar.data.entity.PlayHistory;
import racingcar.data.entity.PlayResult;
import racingcar.presentation.dto.GamePlayHistory;
import racingcar.presentation.dto.GameResultDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingService {

    private final PlayResultRepository playResultRepository;
    private final PlayHistoryRepository playHistoryRepository;

    public RacingService(PlayResultRepository playResultRepository, PlayHistoryRepository playHistoryRepository) {
        this.playResultRepository = playResultRepository;
        this.playHistoryRepository = playHistoryRepository;
    }

    public GameResultDto game(String names, int count) {
        RacingCars racingCars = new RacingCars(names);
        racingCars.startCarRacingGame(count);

        long gameResultId = insertGameResult(racingCars);
        insertPlayHistories(racingCars, gameResultId);

        return new GameResultDto(racingCars);
    }

    private void insertPlayHistories(RacingCars racingCars, long gameResultId) {
        List<PlayHistory> playHistories = racingCars.getCars().stream()
                .map(racingCar -> new PlayHistory(gameResultId, racingCar)).collect(Collectors.toList());
        playHistoryRepository.insertHistory(playHistories);
    }

    private long insertGameResult(RacingCars racingCars) {
        PlayResult playResult = new PlayResult(racingCars.getWinnersNames());
        long gameResultId = playResultRepository.insertGameResult(playResult);
        return gameResultId;
    }

    public List<GamePlayHistory> getGameResults() {
        List<PlayResult> playResults = playResultRepository.getPlayResults();

        List<Long> ids = playResults.stream().map(PlayResult::getId).collect(Collectors.toList());
        List<PlayHistory> playHistories = playHistoryRepository.getPlayHistoriesWhereIn(ids);

        return playResults.stream().map(playResult -> {

            GamePlayHistory gamePlayHistory = new GamePlayHistory();
            gamePlayHistory.setWinners(playResult.getWinners());

            List<GamePlayHistory.RacingCar> racingCars = playHistories.stream().filter(playHistory ->
                    playResult.getId() == playHistory.getPlayResultId()).map(r ->
                    gamePlayHistory.new RacingCar(r.getPlayerName(), r.getPosition())).collect(Collectors.toList());
            gamePlayHistory.setRacingCars(racingCars);
            return gamePlayHistory;
        }).collect(Collectors.toList());
    }
}