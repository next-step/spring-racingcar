package racingcar.view;

import racingcar.domain.Cars;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Cars getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String[] namesArray = scanner.next().split(",");
        return makeCars(namesArray);
    }

    public static int getTryNo() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        return Integer.parseInt(scanner.next());
    }

    private static Cars makeCars(String[] namesArray) {
        return new Cars(namesArray);
    }
}