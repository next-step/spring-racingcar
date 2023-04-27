package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingCarGame {

    private List<Car> racingCars;
    private int totalTry;
    private int defaultMaxPosition = 0;
    private String winners;

    public void setWinners(String winners) {
        this.winners = winners;
    }


    public List<Car> getRacingCars() {
        return racingCars;
    }

    public int getTotalTry() {
        return totalTry;
    }


    public String getWinners() {
        return winners;
    }


    public RacingCarGame(String[] carNames, int num) {
        this.racingCars = Arrays.stream(carNames).map(Car::new).collect(Collectors.toList());
        this.totalTry = num;
    }


    public RacingCarGame start() {
        IntStream.range(0, totalTry)
                .forEach(i -> {
                    racingCars.forEach(Car::move);
                });

        int maxPosition = getMaxPosition(racingCars);

        List<String> winnersString = printWinner(maxPosition);

        winners = String.join(", ", winnersString);


        //totalTry  이동횟수랑  winners - 우승자

        return this;
    }


    // 최종우승자 리스트로 리턴
    private List<String> printWinner(int maxPosition) {
        return racingCars.stream().filter(car -> car.getPosition() == maxPosition).map(Car::getName).collect(Collectors.toList());
    }


    // 제일 많이 움직인 포지션 리턴
    private int getMaxPosition(List<Car> cars) {
        cars.stream().map(Car::getPosition).filter(position -> position > defaultMaxPosition).forEach(position -> defaultMaxPosition = position); //  최고 맥스값 선정
        return defaultMaxPosition;
    }

}
