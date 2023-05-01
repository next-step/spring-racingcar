package racingcar.domain.plays;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlaysRepository {
    private final JdbcTemplate jdbcTemplate;

    public void insertPlayResult(String winners, int count) {
        String sql = "INSERT INTO PLAY_RESULT (WINNERS, COUNT) VALUES (?, ?)";
        jdbcTemplate.update(sql, winners, count);
    }

    public void insertPlayPosition(String name, int position) {
        String sql = "INSERT INTO PLAY_POSITION (NAME, POSITION) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, position);
    }
}
