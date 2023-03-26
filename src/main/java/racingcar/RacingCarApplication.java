package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import racingcar.service.RacingCarService;
import racingcar.view.RacingInputCarView;

@SpringBootApplication
@EnableJpaAuditing
public class RacingCarApplication implements CommandLineRunner {
	@Autowired
	RacingCarService racingCarService;

	public static void main(String[] args) {
		SpringApplication.run(RacingCarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			String carNames = RacingInputCarView.getCarNames();
			int targetDistance = RacingInputCarView.getTargetDistance();

			racingCarService.startRacing(carNames, targetDistance);
		}
	}
}
