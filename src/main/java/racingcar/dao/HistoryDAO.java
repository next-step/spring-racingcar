package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.History;

import java.util.List;

@Repository
public class HistoryDAO {
    private JdbcTemplate jdbcTemplate;

    public HistoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int insertPlayResult(Car car, int groupId) {
        String sql = "insert into PLAY_RESULT (name,position, group_id ) values (?, ?,?)";
        return jdbcTemplate.update(sql, car.getName(), car.getPosition(), groupId);
    }




    public List<Car> selectListPlay(History historyParameter) {
        String sql = "SELECT name,  position  FROM PLAY_RESULT \n" +
                "WHERE group_id = ?";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    Car car = new Car(
                            rs.getString("name"),
                            rs.getInt("position")
                    );
                    return car;
                }, historyParameter.getGroupId());
    }

}
