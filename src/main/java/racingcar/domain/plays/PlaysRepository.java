package racingcar.domain.plays;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlaysRepository {
    private final JdbcTemplate jdbcTemplate;

    public void insert(String winners) {
        String sql = "INSERT INTO PLAY_RESULT (WINNERS) VALUES (?)";
        jdbcTemplate.update(sql, winners);
    }
}
