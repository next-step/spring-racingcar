package racingcar.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import racingcar.RacingCar;
import racingcar.presentation.dto.GameResultDto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RacingRepositoryTest {

    @Autowired
    private RacingRepository racingRepository;

    @Test
    void insertGameResultTest() {
        // given
        String winners = "A";
        List<RacingCar> racingCars = Arrays.asList(new RacingCar(winners));
        GameResultDto gameResultDto = new GameResultDto(winners, racingCars);

        // when
        assertDoesNotThrow(() -> racingRepository.insertGameResult(gameResultDto));
        // then
    }

    @Test
    void insertGameResultFailTest() {
        // given
        String winners = null;
        List<RacingCar> racingCars = Arrays.asList(new RacingCar("A"));
        GameResultDto gameResultDto = new GameResultDto(winners, racingCars);

        // when
        assertThrowsExactly(DataIntegrityViolationException.class,
                () -> racingRepository.insertGameResult(gameResultDto));
        // then
    }

}