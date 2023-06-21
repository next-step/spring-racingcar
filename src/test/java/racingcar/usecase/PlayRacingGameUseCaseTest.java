package racingcar.usecase;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import racingcar.IntegrationTest;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RacingPlayerRepository;

public abstract class PlayRacingGameUseCaseTest extends IntegrationTest {


    @Autowired
    private RacingPlayerRepository racingPlayerRepository;
    @Autowired
    private RacingGameRepository racingGameRepository;
    @AfterEach
    void reset() {
        racingPlayerRepository.deleteAll();
        racingGameRepository.deleteAll();
    }
}
