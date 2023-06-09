package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import racingcar.controller.RacingGameController;
import racingcar.controller.request.ApiCreateRacingGameRequest;
import racingcar.controller.response.ApiCreateRacingGameResponse;

import java.util.Scanner;

@Component
public class RacingCarCommandLineRunner implements CommandLineRunner {

    private final RacingGameController racingGameController;

    public RacingCarCommandLineRunner(RacingGameController racingGameController) {
        this.racingGameController = racingGameController;
    }

    @Override
    public void run(String... args) throws Exception {
        // 자동차 입력
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String nameString = scanner.nextLine();

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();

        // 우승자 조회
        ApiCreateRacingGameRequest request = new ApiCreateRacingGameRequest(nameString, count);
        ResponseEntity<ApiCreateRacingGameResponse> response = racingGameController.createRacingGame(request);

        ApiCreateRacingGameResponse body = response.getBody();

        System.out.println();
        System.out.println("최종 우승자: " + body.getWinners());
        System.out.println("플레이어별 최종 이동 거리: ");
        body.getRacingCars().forEach(System.out::println);
    }
}
