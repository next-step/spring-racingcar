package racingcar;

import racingcar.jdbc.repository.ConsoleRepository;
import racingcar.service.RacingService;
import java.util.List;
import java.util.Scanner;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {

        RacingService racingService = new RacingService(new ConsoleRepository());

        // 자동차 입력
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        List<RacingCar> racingCars = racingService.createCar(scanner.nextLine());

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();

        // 경주 시작
        for (int i = 0; i < count; i++) {
            racingService.playRound(racingCars);
        }

        List<String> winners = racingService.getWinner(racingCars);

        // 우승자 조회
        System.out.println();
        System.out.println("최종 우승자: " + String.join(", ", winners));
    }
}