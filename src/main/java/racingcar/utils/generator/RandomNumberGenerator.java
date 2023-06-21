package racingcar.utils.generator;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RandomNumberGenerator implements NumberGenerator{
    private final double p = 0.6; // 확률

    /**
     * 레이싱 결과물을 구한다.
     * @param size 리스트 크기
     * @param count 차수
     * @return 레이싱 결과물
     */
    public List<Integer> calculatePositions(int size, int count) {
        return IntStream.range(0, size).mapToObj(i -> this.calculatePosition(count)).collect(Collectors.toList());
    }

    /**
     * 차수에 따른 이동 거리를 계산한다.
     * @param n 차수
     * @return 이동 거리
     */
    public int calculatePosition(int n) {
        return generateRandomNumber(calculateMean(n, p), calculateSqrt(n, p));
    }

    /**
     * 평균을 계산한다.
     * @param n 차수
     * @param p 확률
     * @return 평균
     */
    private double calculateMean(int n, double p) {
        return n * p;
    }

    /**
     * 표준편차를 계산한다.
     * @param n 차수
     * @param p 확률
     * @return 표준편차
     */
    private double calculateSqrt(int n, double p) {
        double sqrt = Math.sqrt(n * p * (1 - p));

        if (Double.isNaN(sqrt)) {
            throw new IllegalArgumentException("표준편차가 NaN입니다.");
        } else {
            return sqrt;
        }
    }

    /**
     * 평균과 표준편차를 이용하여 난수를 생성한다.
     * @param mean 평균
     * @param stddev 표준편차
     * @return 난수
     */
    private int generateRandomNumber(double mean, double stddev) {
        double gaussian = getGaussian();
        return (int) Math.round(mean + stddev * gaussian);
    }

    /**
     * 가우시안 분포를 따르는 난수를 생성한다.
     * @return 난수
     */
    private static double getGaussian() {
        Random random = new Random();
        return random.nextGaussian();
    }
}
