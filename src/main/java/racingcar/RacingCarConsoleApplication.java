package racingcar;

import org.springframework.stereotype.Component;
import racingcar.business.RacingService;
import racingcar.presentation.dto.GameResultDto;

import java.util.Scanner;

@Component
public class RacingCarConsoleApplication {

    private static RacingService racingService = null;

    public RacingCarConsoleApplication(RacingService racingService) {
        this.racingService = racingService;
    }

    public static void run() {
        // 자동차 입력
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String names = scanner.nextLine();

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();

        // 경주 시작
        GameResultDto result = racingService.game(names, count);

        // 우승자 조회
        System.out.println();
        System.out.println("최종 우승자: " + String.join(", ", result.getWinners()));
    }
}
