package racingcar.jdbc.dao.query;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.jdbc.PlayResult;


@Repository
@RequiredArgsConstructor
public class PlayResultQueryDao {

    private final JdbcTemplate jdbcTemplate;

    public String findWinnerById(Long id) {
        String sql = "select winners from play_result where play_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> {
                    PlayResult playResult = new PlayResult(
                            resultSet.getString("winners")
                    );
                    return playResult.getWinners();
                }, id);
    }

    public int count() {
        String sql = "select count(*) from play_result";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
