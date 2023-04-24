package racingCar.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import racingCar.vo.PlayResult;

public class PlayResultMapper implements RowMapper<PlayResult> {

    @Override
    public PlayResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        PlayResult result = new PlayResult();
        result.setId(rs.getInt("id"));
        result.setWinners(rs.getString(2));

        return result;
    }
}
