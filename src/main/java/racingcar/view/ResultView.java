package racingcar.view;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static void printCarsLocation(final List<Car> participationCars) {
        participationCars.forEach(car -> System.out.println("이름 : " + car.getName() + ", 위치 : " + car.getPosition()));
        System.out.println();
    }

    public static void printRacingWinners(List<Car> racingWinners) {
        System.out.print("최종 우승자 : ");
        String winners = racingWinners.stream().map(Car::toString).collect(Collectors.joining(", "));
        System.out.println(winners);
    }
}
