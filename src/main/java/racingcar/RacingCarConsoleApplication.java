package racingcar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import racingcar.application.GameService;
import racingcar.infra.PlayHistoryCacheDao;
import racingcar.infra.PlayResultCacheDao;
import racingcar.presentation.dto.PlayRequest;
import racingcar.presentation.dto.PlayResponse;

import java.util.Scanner;

public class RacingCarConsoleApplication {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        GameService gameService = new GameService(new PlayResultCacheDao(), new PlayHistoryCacheDao());

        PlayRequest playRequest = inputPlayRequest();
        PlayResponse response = gameService.play(playRequest);
        printResult(response);
    }

    private static PlayRequest inputPlayRequest() {
        String names = inputRacingCars();
        int playCount = inputPlayCount();
        return new PlayRequest(names, playCount);
    }

    private static String inputRacingCars() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return SCANNER.nextLine();
    }

    private static int inputPlayCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        return SCANNER.nextInt();
    }

    private static void printResult(PlayResponse response) {
        String result = null;
        try {
            result = MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(response);
        } catch (JsonProcessingException e) {
            System.out.println("결과 출력 중 에러가 발생하였습니다.");
        }

        System.out.println();
        System.out.println("경기 결과: \n" + result);
    }

}
