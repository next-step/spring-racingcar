package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SpringConfig {

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate();
    }
}
