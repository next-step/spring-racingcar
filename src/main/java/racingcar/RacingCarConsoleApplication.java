package racingcar;

import racingcar.domain.RacingCarGame;
import racingcar.domain.RacingCars;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        // 자동차 입력
        Scanner scanner = new Scanner(System.in);

        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        List<String> racingCarNames = Arrays.stream(scanner.nextLine().split(","))
                .collect(Collectors.toList());

        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();

        RacingCars racingCars = RacingCars.of(racingCarNames);

        RacingCarGame racingCarGame = RacingCarGame.of(racingCars, count);
        racingCarGame.play();

        String winners = racingCarGame.winners();

        System.out.println();
        System.out.println("최종 우승자: " + winners);
    }
}
