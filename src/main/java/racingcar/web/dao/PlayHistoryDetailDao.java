package racingcar.web.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.web.entity.PlayHistoryDetail;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PlayHistoryDetailDao {

    private final JdbcTemplate jdbcTemplate;

    public Optional<PlayHistoryDetail> findById(Long id) {
        String sql = "SELECT * FROM play_history_detail WHERE id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, playHistoryDetailRowMapper(), id));
    }

    public List<PlayHistoryDetail> findByPlayHistoryId(Long id) {
        String sql = "SELECT * FROM play_history_detail WHERE play_history_id = ?";

        return jdbcTemplate.query(sql, playHistoryDetailRowMapper(), id);
    }

    public Long insert(PlayHistoryDetail playHistoryDetail) {
        String sql = "INSERT INTO play_history_detail (play_history_id, name, position) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, playHistoryDetail.getPlayHistoryId());
            ps.setString(2, playHistoryDetail.getName());
            ps.setInt(3, playHistoryDetail.getPosition());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private RowMapper<PlayHistoryDetail> playHistoryDetailRowMapper() {
        return (resultSet, rowNum) -> new PlayHistoryDetail(
                resultSet.getLong("id"),
                resultSet.getLong("play_history_id"),
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
    }

}
