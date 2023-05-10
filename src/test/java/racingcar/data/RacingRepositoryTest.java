package racingcar.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.data.entity.PlayResult;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest
class RacingRepositoryTest {

    @Autowired
    private PlayResultRepositoryImpl racingRepository;

    @DisplayName("게임 결과 성공 케이스")
    @Test
    void insertGameResultTest() {
        // given
        String winners = "A";
        PlayResult playResult = new PlayResult(winners);

        // when
        // then
        assertDoesNotThrow(() -> racingRepository.insertGameResult(playResult));
    }

    @DisplayName("게임 결과 실패 케이스")
    @Test
    void insertGameResultFailTest() {
        // given
        PlayResult playResult = new PlayResult(null);

        // when
        // then
        assertThrowsExactly(RuntimeException.class,
                () -> racingRepository.insertGameResult(playResult));
    }

}