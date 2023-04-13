package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.domain.CarMoveDeterminer;
import racingcar.domain.RandomCarMoveDeterminer;

@Configuration
public class CarMoveDeterminerConfig {
    @Bean
    public CarMoveDeterminer randomDeterminer() {
        return new RandomCarMoveDeterminer();
    }

}
