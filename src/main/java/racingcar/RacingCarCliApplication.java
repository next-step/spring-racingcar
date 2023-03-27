package racingcar;

import racingcar.service.RacingCliCarService;
import racingcar.view.RacingInputCarView;

public class RacingCarCliApplication {

	public static void main(String... args) throws Exception {
		RacingCliCarService racingCliCarService = new RacingCliCarService();

		while (true) {
			String carNames = RacingInputCarView.getCarNames();
			int targetDistance = RacingInputCarView.getTargetDistance();

			racingCliCarService.startRacing(carNames, targetDistance);
		}
	}
}
