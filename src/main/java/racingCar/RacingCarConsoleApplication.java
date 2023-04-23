package racingCar;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import racingCar.domain.RacingCar;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        // 자동차 입력
        Scanner scanner = new Scanner(System.in);

        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        List<RacingCar> racingCars = Arrays.stream(scanner.nextLine().split(","))
            .map(it -> new RacingCar(it.trim()))
            .collect(Collectors.toList());

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();

        RacingGameService racingGameService = new RacingGameService();

        // 경주 시작
        for (int i = 0; i < count; i++) {
            racingGameService.playRound(racingCars);
        }

        // 우승자 조회
        int maxPosition = racingGameService.findMaxPosition(racingCars);
        List<String> winners = racingGameService.getWinner(racingCars, maxPosition);

        System.out.println();
        System.out.println("최종 우승자: " + String.join(", ", winners));
    }


}