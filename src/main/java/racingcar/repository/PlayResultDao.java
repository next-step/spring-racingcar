package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Integer save(String winners, Integer count) {
        String sql = "INSERT INTO PLAY_RESULT (WINNERS, TRIAL_COUNT) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});

            ps.setString(1, winners);
            ps.setInt(2, count);

            return ps;
        }, keyHolder);

        return (Integer) keyHolder.getKey();
    }

    public List<PlayResult> findAll() {
        String sql = "SELECT * FROM PLAY_RESULT";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PlayResult playResult = new PlayResult();

            playResult.setId(rs.getInt("ID"));
            playResult.setWinners(rs.getString("WINNERS"));

            return playResult;
        });
    }
}
