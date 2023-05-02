package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.entity.RacingGame;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RacingPlayerRepository;

import java.util.Random;

@Service
public class RacingGameService {
    private final RacingGameRepository racingGameRepository;


    @Autowired
    public RacingGameService(RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    public RacingGame createRacingGame(int trialCount) {
        RacingGame racingGame = new RacingGame(trialCount);
        racingGameRepository.save(racingGame);
        return racingGame;
    }
}
