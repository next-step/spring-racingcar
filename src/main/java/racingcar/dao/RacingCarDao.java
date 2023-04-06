package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.PlayResult;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RacingCarDao {

    private static JdbcTemplate jdbcTemplate;
    private static SimpleJdbcInsert insertPlayResult;
    private static SimpleJdbcInsert insertPlayCarHistory;

    public RacingCarDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

        //게임 세팅
        this.insertPlayResult = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");

        //참여한 차정보
        this.insertPlayCarHistory = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_CAR_HISTORY");

    }
    public int insertPlayResult(int round, String winners){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("round", round);
        parameters.put("winners", winners);
        parameters.put("created_at", LocalDateTime.now());
        return insertPlayResult.executeAndReturnKey(parameters).intValue(); //게임회차 id return
    }

    public void insertPlayCarHistory(int id, List<Car> car) {
        for (Car racingCar : car) {
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("id", id);
            parameters.put("name", racingCar.getName());
            parameters.put("position", racingCar.getPosition());
            parameters.put("created_at", LocalDateTime.now());

            insertPlayCarHistory.execute(parameters);
        }
    }

    //실행결과
    public PlayResult getPlayResult(int id) {
        String sql = "SELECT round, winners FROM PLAY_RESULT where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> {
                    PlayResult result = new PlayResult(
                            resultSet.getInt("round"),
                            resultSet.getString("winners"),
                            getCars(id)
                    );
                    return result;
                }, id);
    }

    //모든 실행결과
    public List<PlayResult> getAllPlayResult() {
        String sql = "SELECT id,round, winners FROM PLAY_RESULT";
        return jdbcTemplate.query(sql
                , (resultSet, rowNum) -> new PlayResult(
                        resultSet.getInt("round"),
                        resultSet.getString("winners"),
                        getCars(resultSet.getInt("id"))));
    }

    //해당 게임에 참여한 차정보
    public List<Car> getCars(int id) {
        String sql = "SELECT name, position FROM PLAY_CAR_HISTORY where id = ? ";
        return jdbcTemplate.query(sql
                , (resultSet, rowNum) -> new Car(
                        resultSet.getString("name")
                        , resultSet.getInt("position"))
                , id);

    }
}