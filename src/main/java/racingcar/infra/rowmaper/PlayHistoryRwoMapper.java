package racingcar.infra.rowmaper;

import org.springframework.jdbc.core.RowMapper;
import racingcar.domain.PlayHistory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayHistoryRwoMapper implements RowMapper<PlayHistory> {

    @Override
    public PlayHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PlayHistory.builder()
                .id(rs.getInt("id"))
                .playResultId(rs.getInt("play_result_id"))
                .name(rs.getString("name"))
                .position(rs.getInt("position"))
                .build();
    }

}
