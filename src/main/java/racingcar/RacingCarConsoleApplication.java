package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import racingcar.controller.RacingGameController;
import racingcar.controller.request.ApiCreateRacingGameRequest;
import racingcar.controller.response.ApiCreateRacingGameResponse;
import racingcar.service.GetGamePlayListService;
import racingcar.service.PlayRacingGameService;
import racingcar.utils.generator.RandomNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        PlayRacingGameService playRacingGameService = new PlayRacingGameService(new RacingPlayerRepositoryDummy(), new RandomNumberGenerator(), new RacingGameRepositoryDummy());
        RacingGameController controller = new RacingGameController(playRacingGameService, new GetGamePlayListDummy());
        // 자동차 입력
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String nameString = scanner.nextLine();

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();


        // 우승자 조회
        ApiCreateRacingGameRequest request = new ApiCreateRacingGameRequest(nameString, count);
        ResponseEntity<ApiCreateRacingGameResponse> response = controller.createRacingGame(request);

        ApiCreateRacingGameResponse body = response.getBody();

        System.out.println();
        System.out.println("최종 우승자: " + body.getWinners());
        System.out.println("플레이어별 최종 이동 거리: ");
        body.getRacingCars().forEach(System.out::println);

    }

}