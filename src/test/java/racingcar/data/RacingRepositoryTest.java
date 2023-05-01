package racingcar.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import racingcar.RacingCars;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest
class RacingRepositoryTest {

    @Autowired
    private RacingRepository racingRepository;

    @DisplayName("게임 결과 성공 케이스")
    @Test
    void insertGameResultTest() {
        // given
        String winners = "A";
        RacingCars racingCars = new RacingCars(winners);

        // when
        // then
        assertDoesNotThrow(() -> racingRepository.insertGameResult(racingCars));
    }

    @DisplayName("게임 결과 실패 케이스")
    @Test
    void insertGameResultFailTest() {
        // given
        String winners = null;
        RacingCars racingCars = new RacingCars(winners);

        // when
        // then
        assertThrowsExactly(DataIntegrityViolationException.class,
                () -> racingRepository.insertGameResult(racingCars));
    }

}