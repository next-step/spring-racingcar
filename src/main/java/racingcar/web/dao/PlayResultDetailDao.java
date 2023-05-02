package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.web.entity.PlayResultDetail;

import java.sql.PreparedStatement;

@Repository
public class PlayResultDetailDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDetailDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PlayResultDetail findById(Long id) {
        String sql = "SELECT * FROM play_result_detail WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, playResultDetailRowMapper(), id);
    }

    public Long insert(PlayResultDetail playResultDetail) {
        String sql = "INSERT INTO PLAY_RESULT_DETAIL (play_result_id, name, position) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, playResultDetail.getPlayResultId());
            ps.setString(2, playResultDetail.getName());
            ps.setInt(3, playResultDetail.getPosition());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private RowMapper<PlayResultDetail> playResultDetailRowMapper() {
        return (resultSet, rowNum) -> new PlayResultDetail(
                resultSet.getLong("id"),
                resultSet.getLong("play_result_id"),
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
    }

}
