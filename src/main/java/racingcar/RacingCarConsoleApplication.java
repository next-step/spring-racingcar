package racingcar;

import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.service.RacingCarService;
import racingcar.persistence.PlayResultDAO;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        // 자동차 입력
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String names = scanner.nextLine();

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();

        // 경주 시작
        RacingCars racingCars = new RacingCars(names);
        racingCars.playRound(count);

        System.out.println();
        List<String> winners = racingCars.findWinners();
        System.out.println("최종 우승자: " + String.join(", ", winners));
    }
}