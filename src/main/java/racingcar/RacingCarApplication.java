package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import racingcar.service.RacingCarService;

@SpringBootApplication
@EnableJpaAuditing
public class RacingCarApplication {
	@Autowired
	RacingCarService racingCarService;

	public static void main(String[] args) {
		SpringApplication.run(RacingCarApplication.class, args);
	}
}
