package racingcar;

import racingcar.domain.RacingCars;
import racingcar.dto.RacingPlaysRequest;

import java.util.Scanner;

public class RacingCarConsoleApplication {

    public static String racing(String names, int count){
        RacingPlaysRequest request = new RacingPlaysRequest(names, count);
        RacingCars racingCars = new RacingCars(request.getConvertRequestNameToCarList());

        racingCars.playRound(count);

        return racingCars.getWinnersToString();
    }

    public static void main(String[] args) {

        // 자동차 입력
        Scanner scanner = new Scanner(System.in);

        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String names = scanner.nextLine();

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();


        System.out.println();
        System.out.println("최종 우승자: " + racing(names, count));
    }

}
