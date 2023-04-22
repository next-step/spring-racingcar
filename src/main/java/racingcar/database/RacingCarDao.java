package racingcar.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.racing.RacingCar;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RacingCarDao {

    public static final String RACING_HISTORY_TABLENAME = "RACING_HISTORY";
    public static final String RACING_RESULT_TABLENAME = "RACING_RESULT";
    private static JdbcTemplate jdbcTemplate;

    private static SimpleJdbcInsert insertRacingResult;
    private static SimpleJdbcInsert insertRacingHistory;

    public RacingCarDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertRacingHistory = new SimpleJdbcInsert(dataSource)
                .withTableName(RACING_HISTORY_TABLENAME)
                .usingGeneratedKeyColumns("id");

        this.insertRacingResult = new SimpleJdbcInsert(dataSource)
                .withTableName(RACING_RESULT_TABLENAME)
                .usingGeneratedKeyColumns("id");
    }

    public static RaceResult setRacingResult(RaceResult racingResult, int raceRound) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", racingResult.getCount());
        parameters.put("winners", racingResult.getWinners().toString());
        parameters.put("created_at", LocalDateTime.now());
        parameters.put("race_round", raceRound);
        insertRacingResult.execute(parameters);

        return racingResult;
    }

    public static void setRacingHistory(RacingCar car, int raceRound) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("player", car.getDriver().getName());
        parameters.put("position", car.getPosition().getPoistion());
        parameters.put("played_at", LocalDateTime.now());
        parameters.put("race_round", raceRound);
        insertRacingHistory.execute(parameters);
    }

    //모든 실행결과
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public static List<RaceHistory> getGameHistory() {
        String sql = "SELECT TRIAL_COUNT, WINNERS FROM RACING_RESULT";

        return jdbcTemplate.query(sql
                , (resultSet, rowNum) -> new RaceHistory(
                        resultSet.getInt("TRIAL_COUNT")
                        , Collections.singletonList(resultSet.getString("WINNERS"))));


    }

}
