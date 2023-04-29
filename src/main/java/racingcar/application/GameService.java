package racingcar.application;

import org.springframework.stereotype.Service;
import racingcar.domain.PlayHistory;
import racingcar.domain.PlayHistoryRepository;
import racingcar.domain.PlayResult;
import racingcar.domain.PlayResultRepository;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomMoveStrategy;
import racingcar.presentation.dto.PlayRequest;
import racingcar.presentation.dto.PlayResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final PlayResultRepository resultRepository;
    private final PlayHistoryRepository historyRepository;

    public GameService(PlayResultRepository resultRepository, PlayHistoryRepository historyRepository) {
        this.resultRepository = resultRepository;
        this.historyRepository = historyRepository;
    }

    public PlayResponse play(PlayRequest request) {
        RacingCars racingCars = RacingCars.of(request.getPlayers());
        int playCount = request.getCount();

        RacingGame racingGame = RacingGame.of(racingCars);
        racingGame.play(playCount, new RandomMoveStrategy());
        List<String> winners = racingGame.getWinners();

        int playResultId = savePlayResult(racingGame, playCount);
        savePlayHistories(playResultId, racingCars);

        return PlayResponse.of(winners, racingCars);
    }

    private int savePlayResult(RacingGame racingGame, int playCount) {
        PlayResult playResult = PlayResult.builder()
                .winners(racingGame.getWinners())
                .trialCount(playCount)
                .build();
        return resultRepository.save(playResult);
    }

    private void savePlayHistories(int playResultId, RacingCars racingCars) {
        List<PlayHistory> playHistories = racingCars.getValue()
                .stream()
                .map(it -> PlayHistory.of(playResultId, it))
                .collect(Collectors.toList());

        historyRepository.save(playHistories);
    }

}
