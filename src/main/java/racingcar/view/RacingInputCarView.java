package racingcar.view;

import java.util.Scanner;

public class RacingInputCarView {

    public static String getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        Scanner scan = new Scanner(System.in);

        return scan.nextLine();
    }

    public static int getTargetDistance() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        Scanner scan = new Scanner(System.in);
        return Integer.parseInt(scan.nextLine());
    }

}
