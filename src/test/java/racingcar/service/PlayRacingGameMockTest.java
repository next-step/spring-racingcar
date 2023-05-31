package racingcar.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Spy;
import racingcar.MockTest;
import racingcar.ServiceTest;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RacingPlayerRepository;
import racingcar.utils.generator.RandomNumberGenerator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public abstract class PlayRacingGameMockTest extends ServiceTest {

    @Mock
    RacingGameRepository racingGameRepository;
    @Mock
    RacingPlayerRepository racingPlayerRepository;

    @Spy
    RandomNumberGenerator randomNumberGenerator;

}
