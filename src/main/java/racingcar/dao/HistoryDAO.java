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


    public int insert(Car car, int trialCount, String winners) {
        String sql = "insert into PLAY_RESULT (winners, trialCount,name,position) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, winners, trialCount, car.getName(), car.getPosition());
    }

    public List<History> selectListPlayResult() {
        String sql = "SELECT  FORMATDATETIME(created_at,'yyyymmddhhmmss') created_at   , winners, trialcount ,max(position) position  FROM PLAY_RESULT \n" +
                "group by FORMATDATETIME(created_at,'yyyymmddhhmmss') , winners, trialcount";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    History history = new History(
                            rs.getString("winners"),
                            rs.getInt("trialCount"),
                            rs.getInt("position"),
                            rs.getString("created_at")
                    );
                    return history;
                });
    }

    public List<Car> selectListPlay(History historyParameter) {
        String sql = "SELECT name,  position  FROM PLAY_RESULT \n" +
                "WHERE FORMATDATETIME(created_at,'yyyymmddhhmmss') =  ? \n" +
                "and    winners = ?  \n" +
                "and    trialcount =  ?";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    Car car = new Car(
                            rs.getString("name"),
                            rs.getInt("position")
                    );
                    return car;
                }, historyParameter.getCreated_at()
                , historyParameter.getWinners()
                , historyParameter.getTrialCount());
    }

}
