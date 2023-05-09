package racingcar;

import java.util.Scanner;
import racingcar.game.application.GameService;
import racingcar.game.dao.MemoryPlayHistoryDao;
import racingcar.game.dao.MemoryPlayResultDao;
import racingcar.game.application.dto.GameResult;
import racingcar.game.application.dto.GameStartData;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        // 자동차 입력
        String names = InputView.getRacingCarNames();

        // 시도 횟수 입력
        int count = InputView.getCount();

        // 경주 시작
        GameService gameService = new GameService(new MemoryPlayResultDao(), new MemoryPlayHistoryDao());
        GameResult gameResult = gameService.play(new GameStartData(names, count));

        // 우승자 조회
        OutputView.printGameResult(gameResult);
    }

    static class InputView {

        private static final Scanner SCANNER = new Scanner(System.in);

        public static String getRacingCarNames() {
            System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
            return SCANNER.nextLine();
        }

        public static int getCount() {
            System.out.println("시도할 횟수는 몇 회인가요?");
            return SCANNER.nextInt();
        }
    }

    static class OutputView {

        public static void printGameResult(GameResult gameResult) {
            System.out.println();
            System.out.println("최종 우승자: " + gameResult.getWinners());
        }
    }
}
