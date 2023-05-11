package racingcar.service.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.domain.strategy.MovingStrategyType;

@Configuration
public class MovingStrategyConfig {

    @ConditionalOnMissingBean
    @Bean
    public MovingStrategy randomMovingStrategy() {
        return MovingStrategyType.getStrategy(MovingStrategyType.RANDOM);
    }

}
