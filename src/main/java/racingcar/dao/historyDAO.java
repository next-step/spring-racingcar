package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Device;

@Repository
public class historyDAO {
    private JdbcTemplate jdbcTemplate;

    /**
     * public int update(String sql, @Nullable Object... args)
     */
    public int insert(Device device) {
        String sql = "insert into PLAY_RESULT (winners, count) values (?, ?)";
         return jdbcTemplate.update(sql,device.getNames() , device.getCount());
    }
}
