package racingcar;

import racingcar.dtos.request.PlaysRequestDto;
import racingcar.dtos.response.PlaysResponseDto;
import racingcar.repository.RacingCarPlayConsoleDaoImpl;
import racingcar.service.PlaysService;

import java.util.*;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        // 자동차 입력
        Scanner scanner = new Scanner(System.in);
        PlaysService playsService = new PlaysService(new RacingCarPlayConsoleDaoImpl());
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String racingCars = scanner.nextLine();
        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int trialCount = scanner.nextInt();

        // 경주 시작
        PlaysRequestDto requestDto = new PlaysRequestDto(racingCars, trialCount);
        PlaysResponseDto play = playsService.play(requestDto);

        // 우승자 조회
        System.out.println("최종 우승자: " + String.join(", ", play.getWinners()));

    }
}
