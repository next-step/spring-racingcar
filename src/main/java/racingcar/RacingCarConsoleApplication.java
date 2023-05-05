package racingcar;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.RaceResult;
import racingcar.domain.RacingCar;
import racingcar.repository.RacingCarRepository;
import racingcar.service.RacingCarService;

public class RacingCarConsoleApplication {

  public static void main(String[] args) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    RacingCarRepository racingCarRepository = new RacingCarRepository(jdbcTemplate);
    RacingCarService racingCarService = new RacingCarService(racingCarRepository);

    // 자동차 입력
    Scanner scanner = new Scanner(System.in);
    System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    List<RacingCar> racingCars = Arrays.stream(scanner.nextLine().split(","))
        .map(it -> new RacingCar(it.trim()))
        .collect(Collectors.toList());

    // 시도 횟수 입력
    System.out.println("시도할 횟수는 몇 회인가요?");
    int count = scanner.nextInt();

    // 경주 시작
    for (int i = 0; i < count; i++) {
      racingCarService.playRound(racingCars);
    }

    // 우승자 조회
    RaceResult raceResult = new RaceResult(racingCars);
    String winner = raceResult.getWinners();

    System.out.println();
    System.out.println("최종 우승자: " + winner);
  }

}