package racingcar;

import racingcar.domain.entity.RacingCars;
import racingcar.domain.service.RacingCarGameService;
import racingcar.infra.inmemory.InMemoryPlayResultRepository;

import java.util.List;
import java.util.Scanner;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        // 자동차 입력
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String names = scanner.nextLine();
        RacingCars racingCars = RacingCars.from(names);

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();

        // 경주 시작
        RacingCarGameService racingCarGameService = new RacingCarGameService(new InMemoryPlayResultRepository());
        racingCarGameService.playGame(racingCars, count);

        // 우승자 조회
        List<String> winners = racingCars.getWinners();
        System.out.println();
        System.out.println("최종 우승자: " + String.join(", ", winners));
        racingCars.getRacingCars().forEach(it ->
                System.out.println("name: " + it.getName() + ", " + "position: " + it.getPosition())
        );
    }
}
