package racingcar.infra.rowmaper;

import org.springframework.jdbc.core.RowMapper;
import racingcar.domain.PlayResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayResultRowMapper implements RowMapper<PlayResult> {

    @Override
    public PlayResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PlayResult.builder()
                .id(rs.getInt("id"))
                .winners(rs.getString("winners"))
                .trialCount(rs.getInt("trial_count"))
                .build();
    }

}
