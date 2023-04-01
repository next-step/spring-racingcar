package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingCarGameRepository;

@Transactional
@Service
public class RacingCarGameService {
    private final RacingCarGameRepository racingCarGameRepository;

    public RacingCarGameService(RacingCarGameRepository racingCarGameRepository) {
        this.racingCarGameRepository = racingCarGameRepository;
    }

    public void saveResult(RacingGame racingGame, int count) {
        long playId = racingCarGameRepository.saveResult(count, racingGame);
        racingCarGameRepository.saveDetailResult(playId, racingGame);
    }
}

