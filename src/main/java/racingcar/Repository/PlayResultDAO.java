package racingcar.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.model.PlayResult;

@Repository
public class PlayResultDAO {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<PlayResult> actorRowMapper = (resultSet, rowNum) -> {
        PlayResult playResult = new PlayResult(
            resultSet.getInt("id"),
            resultSet.getInt("group_id"),
            resultSet.getInt("trial_count"),
            resultSet.getString("winners"),
            resultSet.getString("created_at")
        );
        return playResult;
    };

    public PlayResultDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 전체 결과 데이터
     *
     * @return List<PlayResult>
     */
    public List<PlayResult> findAllPlayResult() {
        String sql = "select id, group_id, trial_count, winners, created_at from play_result";
        return jdbcTemplate.query(sql, actorRowMapper);
    }

    /**
     * 아이디로 결과 데이터 찾기
     *
     * @param id
     * @return List<PlayResult>
     */
    public List<PlayResult> findPlayResultById(int id) {
        String sql = "select id, group_id, trial_count, winners, created_at from play_result where id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, id);
    }

    /**
     * 그룹 아이디로 결과 데이터 찾기
     *
     * @param groupId
     * @return List<PlayResult>
     */
    public List<PlayResult> findPlayResultByGroupId(int groupId) {
        String sql = "select id, group_id, trial_count, winners, created_at from play_result where racing_car_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, groupId);
    }

    /**
     * 결과 데이터 삽입
     *
     * @param playResult
     * @return id
     */
    public PlayResult insertPlayResult(PlayResult playResult) {
        String sql = "insert into play_result (group_id, trial_count, winners) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, playResult.getGroupId());
            ps.setInt(2, playResult.getTrialCount());
            ps.setString(3, playResult.getWinners());
            return ps;
        }, keyHolder);

        return new PlayResult(keyHolder.getKey().intValue(), playResult.getGroupId(),
            playResult.getTrialCount(), playResult.getWinners());
    }

    /**
     * 결과 데이터 업데이트
     *
     * @param playResult
     */
    public void updatePlayResult(PlayResult playResult) {
        String sql = "update play_result set group_id = ? , trial_count = ? , winners = ? where id = ?";
        this.jdbcTemplate.update(sql, playResult.getGroupId(), playResult.getTrialCount(),
            playResult.getWinners(), playResult.getId());
    }
}
