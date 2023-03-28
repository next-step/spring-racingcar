package racingcar.view;

import java.util.List;

import racingcar.domain.Car;
import racingcar.model.RacingResponse;

public class RacingResultView {

    public static void printStartRacing() {
        System.out.println("");
        System.out.println("실행 결과");
    }

    public static void printResult(String carNames) {
        System.out.println("최종 우승자: " + carNames);
    }

    public static void printRacingResponse(RacingResponse racingResponse) {
        System.out.println();
        System.out.println("우승자:");
        System.out.println(racingResponse.getWinners());
        System.out.println();
        racingResponse.getRacingCars().forEach(car -> {
            System.out.println("Name: " + car.getName() + ", Position: " + car.getPosition());
        });
        System.out.println();
    }

    public static void printNewLine() {
        System.out.println("");
    }
}
