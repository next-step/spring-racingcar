package racingcar.service;

import racingcar.domain.Car;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingCarGame {

    private List<Car> racingCars;
    private int totalTry;
    private int DEFAULT_MAX_POSITION = 0;

    private String winners;

    public List<Car> getRacingCars() {
        return racingCars;
    }

    public int getTotalTry() {
        return totalTry;
    }

    public int getDEFAULT_MAX_POSITION() {
        return DEFAULT_MAX_POSITION;
    }

    public String getWinners() {
        return winners;
    }

    public RacingCarGame(String[] carNames, int num){
        this.racingCars = Arrays.stream(carNames).map(Car::new).collect(Collectors.toList());
        this.totalTry = num;
    }

    public RacingCarGame start() {
        IntStream.range(0, totalTry)
                .forEach(i -> {
                    racingCars.forEach(Car::move);
                    printPosition();
                });
        System.out.print("우승자 :");

        int maxPosition = getMaxPosition(racingCars);

        List<String> winnerss = printWinner(maxPosition);
        winners = String.join(", ", winnerss);
        System.out.println("최종 우승자: " + winners);

        //totalTry  이동횟수랑  winners - 우승자

        return this;
    }

    private void printPosition() {
        racingCars.forEach(car -> {
            System.out.print(car.getName() + " : ");
            IntStream.range(0, car.getPosition()).forEach(i -> System.out.print("-"));
            System.out.println();
        });
        System.out.println();
    }

    // 최종우승자 리스트로 리턴
    private List<String> printWinner(int maxPosition){
        return racingCars.stream().filter(car->car.getPosition() == maxPosition).map(Car::getName).collect(Collectors.toList());
    }


    // 제일 많이 움직인 포지션 리턴
    private int getMaxPosition(List<Car> cars ){
        cars.stream().map(Car::getPosition).filter(position->position > DEFAULT_MAX_POSITION).forEach(position-> DEFAULT_MAX_POSITION = position); //  최고 맥스값 선정
        return DEFAULT_MAX_POSITION;
    }

}
