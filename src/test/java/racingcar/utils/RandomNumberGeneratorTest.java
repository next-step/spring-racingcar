package racingcar.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import racingcar.ParentTest;
import racingcar.utils.generator.RandomNumberGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RandomNumberGeneratorTest extends ParentTest {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();


    @DisplayName("게임 결과 난수 생성")
    @Nested
    class generateRandom {

        @DisplayName("n이 음수로 들어오면 에러를 발생시킨다.")
        @Test
        void minus() {
            // given
            int n = -5;
            // when

            // then
            Assertions.assertThatThrownBy(() -> randomNumberGenerator.calculatePosition(n)).isInstanceOf(IllegalArgumentException.class);

        }
        @DisplayName("n이 음수로 들어오면 0을 뱉는다.")
        @Test
        void zero() {
            // given
            int n = 0;
            // when
            List<Integer> result = IntStream.range(0, 100000).mapToObj(i -> randomNumberGenerator.calculatePosition(n)).collect(Collectors.toList());

            // then
            long count = result.stream().filter(r -> r == n).count();

            Assertions.assertThat(count).isEqualTo(100000);
        }
        @DisplayName("평균과 표준편차를 이용하여 난수를 생성한다.")
        @Test
        void plus() {
            // given
            int n = 100;
            // when
            List<Integer> result = IntStream.range(0, 100000).mapToObj(i -> randomNumberGenerator.calculatePosition(n)).collect(Collectors.toList());
            // then

            long count = result.stream().filter(r -> r > n).count();

            Assertions.assertThat(count).isEqualTo(0);
        }
    }

}