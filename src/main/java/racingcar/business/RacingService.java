package racingcar.business;

import org.springframework.stereotype.Service;
import racingcar.RacingCars;
import racingcar.data.HistoryRepository;
import racingcar.data.RacingRepository;
import racingcar.presentation.dto.GamePlayHistory;
import racingcar.presentation.dto.GameResultDto;
import racingcar.presentation.dto.GameStartDto;

import java.util.List;

@Service
public class RacingService {

    private final RacingRepository racingRepository;
    private final HistoryRepository historyRepository;

    public RacingService(RacingRepository racingRepository, HistoryRepository historyRepository) {
        this.racingRepository = racingRepository;
        this.historyRepository = historyRepository;
    }

    public GameResultDto game(GameStartDto gameStartDto) {
        RacingCars racingCars = new RacingCars(gameStartDto.getNames());
        int count = gameStartDto.getCount();
        racingCars.startCarRacingGame(count);

        long gameResultId = racingRepository.insertGameResult(racingCars);
        historyRepository.insertHistory(gameResultId, racingCars);

        return new GameResultDto(racingCars);
    }

    public List<GamePlayHistory> getGameResults() {
        return racingRepository.getGameResults();
    }
}