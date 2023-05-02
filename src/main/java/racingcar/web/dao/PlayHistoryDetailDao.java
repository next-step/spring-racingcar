package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.web.entity.PlayHistoryDetail;

import java.sql.PreparedStatement;

@Repository
public class PlayHistoryDetailDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayHistoryDetailDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PlayHistoryDetail findById(Long id) {
        String sql = "SELECT * FROM play_history_detail WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, playHistoryDetailRowMapper(), id);
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
