package racingCar.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import racingCar.vo.PlayResult;
import racingCar.vo.PlayerRecord;

public class PlayerRecordMapper implements RowMapper<PlayerRecord> {

    @Override
    public PlayerRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        PlayerRecord result = new PlayerRecord();
        result.setName(rs.getString(1));
        result.setPosition(rs.getInt(2));

        return result;
    }
}
