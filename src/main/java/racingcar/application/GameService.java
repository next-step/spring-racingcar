package racingcar.application;

import org.springframework.stereotype.Service;
import racingcar.domain.PlayHistoryRepository;
import racingcar.domain.PlayResultRepository;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomMoveStrategy;
import racingcar.presentation.dto.PlayRequest;
import racingcar.presentation.dto.PlayResponse;

import java.util.List;

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

        resultRepository.save(winners, playCount);
        historyRepository.save(racingCars);

        return PlayResponse.of(winners, racingCars);
    }

}
