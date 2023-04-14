package racingcar.view;

import racingcar.api.response.PlayResponse;
import racingcar.domain.RacingGame;

public class ResultView {

    public static void printCarsLocation(final RacingGame racingGame) {
        System.out.println(PlayResponse.extract(racingGame.getParticipationCars(), racingGame.getRacingWinners()));
        System.out.println();
    }
}
