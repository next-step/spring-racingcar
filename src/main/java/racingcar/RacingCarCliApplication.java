package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import racingcar.service.RacingCarService;
import racingcar.view.RacingInputCarView;

@EnableJpaAuditing
@Profile("!test")
public class RacingCarCliApplication implements CommandLineRunner {
	@Autowired
	RacingCarService racingCarService;

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			String carNames = RacingInputCarView.getCarNames();
			int targetDistance = RacingInputCarView.getTargetDistance();

			racingCarService.startRacing(carNames, targetDistance);
		}
	}
}
