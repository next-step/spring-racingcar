package racingcar.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CalculateRaceService {
    private final double p = 0.6; // 확률


    /**
     * 차수에 따른 이동 거리를 계산한다.
     * @param n 차수
     * @return 이동 거리
     */
    public int getMoveDistance(int n) {
        return generateRandomNumber(calculateMean(n, p), calculateSqrt(n, p));
    }

    /**
     * 평균을 계산한다.
     * @param n 차수
     * @param p 확률
     * @return 평균
     */
    public double calculateMean(int n, double p) {
        return n * p;
    }

    /**
     * 표준편차를 계산한다.
     * @param n 차수
     * @param p 확률
     * @return 표준편차
     */
    public double calculateSqrt(int n, double p) {
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
