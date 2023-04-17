package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class GroupDAO {
    private JdbcTemplate jdbcTemplate;

    public GroupDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int insertGroup(int toTalTry , String winners) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection con) -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into RESULT_GROUP(winners , trial_count ) values(?,?)",
                    new String[]{"id"});
            pstmt.setString(1, winners);
            pstmt.setInt(2, toTalTry);
            return pstmt;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public List<History> selectListPlayResult() {
        String sql = "select id, trial_count, winners, created_at from RESULT_GROUP";

        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    History history = new History(
                            rs.getString("winners"),
                            rs.getInt("trial_count"),
                            rs.getInt("id")
                    );
                    return history;
                });
    }

}
