package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.RacingCarResultDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayCarDao {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertPlayHistory;

    private final SimpleJdbcInsert insertPlayCarHistory;

    private final SimpleJdbcInsert insertPlayResult;


    public PlayCarDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertPlayHistory = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_HISTORY")
                .usingGeneratedKeyColumns("id")
                .usingColumns("round");

        this.insertPlayCarHistory = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_CARS_HISTORY")
                .usingColumns("id", "name", "position");

        this.insertPlayResult = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingColumns("id", "winners");
    }


    public int insertPlayhistory(int round) {
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("round", round);

        return insertPlayHistory.executeAndReturnKey(parameters).intValue();
    }

    public void insertPlayCarHistory(int id, Car car) {
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", id);
        parameters.put("name", car.getName());
        parameters.put("position", car.getPosition());

        insertPlayCarHistory.execute(parameters);
    }

    public void insertPlayResult(int id, String winners) {
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", id);
        parameters.put("winners", winners);

        insertPlayResult.execute(parameters);
    }

    public List<RacingCarResultDto> getAllRacingResult() {
        String sql = "SELECT id, winners FROM PLAY_RESULT";

        return jdbcTemplate.query(sql
                , (resultSet, rowNum) -> new RacingCarResultDto(
                        resultSet.getString("winners")
                        , getPlayDto(resultSet.getInt("id")))
        );
    }

    public List<Car> getPlayDto(int id) {
        String sql = "SELECT name, position FROM PLAY_CARS_HISTORY where id = ?";

        return jdbcTemplate.query(sql
                , (resultSet, rowNum) -> new Car(resultSet.getString("name")
                        , resultSet.getInt("position"))
                , id);
    }


}
