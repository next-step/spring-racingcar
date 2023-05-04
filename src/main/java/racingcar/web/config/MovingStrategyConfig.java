package racingcar.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.strategy.MovingStrategy;
import racingcar.strategy.RandomMovingStrategy;

@Configuration
public class MovingStrategyConfig {

    @ConditionalOnMissingBean
    @Bean
    public MovingStrategy movingStrategy() {
        return new RandomMovingStrategy();
    }

}
