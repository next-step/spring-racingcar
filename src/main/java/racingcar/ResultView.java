package racingcar;

import racingcar.model.RacingResponse;

public class ResultView {

    public void printResult(RacingResponse racingResponse) {
        System.out.println("최종 우숭: " + racingResponse.getWinners());
        System.out.println("플레이어별 이동거리");
        racingResponse.getRacingCars().stream()
                .forEach(it -> System.out.println(it.getName() + ": " + it.getPosition()));
    }
}
