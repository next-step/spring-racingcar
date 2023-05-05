package racingcar;

import java.util.Scanner;
import racingcar.game.application.GameService;
import racingcar.game.dao.MemoryPlayHistoryDao;
import racingcar.game.dao.MemoryPlayResultDao;
import racingcar.game.dto.PlayRequest;
import racingcar.game.dto.PlayResultResponse;

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
        GameService gameService = new GameService(new MemoryPlayResultDao(), new MemoryPlayHistoryDao());
        PlayResultResponse playResultResponse = gameService.play(new PlayRequest(names, count));

        // 우승자 조회
        System.out.println();
        System.out.println("최종 우승자: " + playResultResponse.getWinners());
    }
}
