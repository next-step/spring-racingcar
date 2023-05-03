package racingcar.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.strategy.MovingStrategy;

@Configuration
public class TestMovingStrategyConfig {

    @Bean
    public MovingStrategy movingStrategy() {
        return () -> true;
    }

}
