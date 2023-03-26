package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import racingcar.service.RacingCarService;
import racingcar.view.RacingInputCarView;

@SpringBootApplication
@EnableJpaAuditing
@ConditionalOnProperty(prefix = "command.line.runner", value = "enabled", havingValue = "true", matchIfMissing = true)
public class RacingCarApplication {
	@Autowired
	RacingCarService racingCarService;

	public static void main(String[] args) {
		SpringApplication.run(RacingCarApplication.class, args);
	}

	// @Override
	public void run(String... args) throws Exception {
		while (true) {
			String carNames = RacingInputCarView.getCarNames();
			int targetDistance = RacingInputCarView.getTargetDistance();

			racingCarService.startRacing(carNames, targetDistance);
		}
	}
}
