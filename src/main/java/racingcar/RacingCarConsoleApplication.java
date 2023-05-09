package racingcar;

import racingcar.application.RacingCarService;
import racingcar.dto.RacingCarNamePosition;
import racingcar.dto.RacingCarPlayResponse;
import racingcar.repository.InMemoryGameHistoryRepository;
import racingcar.repository.InMemoryRoundHistoryRepository;

import java.util.Scanner;

public class RacingCarConsoleApplication {
  public static void main(String[] args) {
    RacingCarService service =
        new RacingCarService(
            new InMemoryGameHistoryRepository(), new InMemoryRoundHistoryRepository());

    Scanner scanner = new Scanner(System.in);

    System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    String names = scanner.nextLine();

    System.out.println("시도할 횟수는 몇 회인가요?");
    int count = scanner.nextInt();

    RacingCarPlayResponse gameResult = service.play(names, count);

    System.out.println();
    System.out.println("최종 우승자: " + gameResult.getWinners());
    System.out.println();
    System.out.println("== 플레이어별 최종 이동 거리== ");
    for (RacingCarNamePosition racingCar : gameResult.getRacingCars()) {
      System.out.printf("%s: %d\n", racingCar.getName(), racingCar.getPosition());
    }
  }
}
