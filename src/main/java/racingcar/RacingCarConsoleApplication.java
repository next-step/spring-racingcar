package racingcar;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.plays.PlaysRepository;
import racingcar.domain.plays.PlaysService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PlaysRepository playsRepository = new PlaysRepository(jdbcTemplate);
        PlaysService playsService = new PlaysService(playsRepository);

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
        playsService.playRound(count, racingCars);

        // 우승자 조회
        String winners = playsService.getWinners(racingCars);

        System.out.println();
        System.out.println("최종 우승자: " + String.join(", ", winners));
    }
}