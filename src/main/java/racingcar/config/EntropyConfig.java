package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.domain.CarMoveEntropy;
import racingcar.domain.RandomCarMoveEntropy;


@Configuration
public class EntropyConfig {
    @Bean
    public CarMoveEntropy randomEntropy() {
        return new RandomCarMoveEntropy();
    }

}
