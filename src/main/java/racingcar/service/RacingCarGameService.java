package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingGame;
import racingcar.domain.ResultPlay;
import racingcar.repository.RacingCarGameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RacingCarGameService {
    private final RacingCarGameRepository racingCarGameRepository;

    public RacingCarGameService(RacingCarGameRepository racingCarGameRepository) {
        this.racingCarGameRepository = racingCarGameRepository;
    }

    public void play(RacingGame racingGame, int count) {
        racingGame.startGame(count);
        this.saveResult(racingGame);
    }

    private void saveResult(RacingGame racingGame) {
        long playId = racingCarGameRepository.saveResult(racingGame);
        racingCarGameRepository.saveDetailResult(playId, racingGame);
    }
    public List<RacingGame> getAllRacingGames() {
        List<ResultPlay> resultPlays = racingCarGameRepository.racingGameResults();
        return resultPlays.stream()
                .map(racingCarGameRepository::racingGameDetailResults)
                .collect(Collectors.toList());
    }
}

