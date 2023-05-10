package racingcar.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RandomNumberGeneratorTest {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @DisplayName("n이 음수가 들어온다면")
    @Nested
    class negative {
        @DisplayName("calculateMean 메서드는")
        @Nested
        class test {
            @DisplayName("음수로 기댓값을 계산한다.")
            @Test
            void nIsNegative() {
                // given
                int n = -1;
                double p = 0.6;
                // when
                double mean = randomNumberGenerator.calculateMean(n, p);

                // then
                Assertions.assertThat(mean).isEqualTo(-0.6);
            }
        }

        @DisplayName("calculateSqrt 메서드는")
        @Nested
        class sqrt {

            @DisplayName("에러가 발생한다.")
            @Test
            void nIsNegative() {
                // given
                int n = -1;
                double p = 0.6;
                // when

                // then
                Assertions.assertThatThrownBy(() -> randomNumberGenerator.calculateSqrt(n, p)).isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    @DisplayName("n이 0이 들어온다면")
    @Nested
    class zero {
        @DisplayName("calculateMean 메서드는")
        @Nested
        class test {
            @DisplayName("0으로 기댓값을 계산한다.")
            @Test
            void nIsZero() {
                // given
                int n = 0;
                double p = 0.6;
                // when
                double mean = randomNumberGenerator.calculateMean(n, p);

                // then
                Assertions.assertThat(mean).isEqualTo(0);
            }
        }

        @DisplayName("calculateSqrt 메서드는")
        @Nested
        class sqrt {
            @DisplayName("0으로 계산한다.")
            @Test
            void nIsZero() {
                // given
                int n = 0;
                double p = 0.6;
                // when
                double mean = randomNumberGenerator.calculateSqrt(n, p);

                // then
                Assertions.assertThat(mean).isEqualTo(0);
            }

        }

    }

    @DisplayName("n이 양수가 들어온다면")
    @Nested
    class good {
        @DisplayName("calculateMean 메서드는")
        @Nested
        class test {
            @DisplayName("기댓값을 계산한다.")
            @Test
            void calculateMean() {
                // given
                int n = 37;
                double p = 0.6;
                // when
                double mean = randomNumberGenerator.calculateMean(n, p);

                // then
                Assertions.assertThat(mean).isEqualTo(22.2);
            }
        }

        @DisplayName("calculateSqrt 메서드는")
        @Nested
        class sqrt {

            @DisplayName("표준편차를 계산한다.")
            @Test
            void calculateMean() {
                // given
                int n = 37;
                double p = 0.6;
                // when
                double mean = randomNumberGenerator.calculateSqrt(n, p);

                // then
                Assertions.assertThat(mean).isEqualTo(2.979932885150268);
            }
        }

    }

    @Nested
    @DisplayName("getMoveDistance 메서드는")
    class getMoveDistance {
        @DisplayName("평균과 표준편차를 이용하여 난수를 생성한다.")
        @Test
        void getMoveDistanceTest() {
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