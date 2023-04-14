package racingcar.dao;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.RacingHistory;
import racingcar.dto.RacingResult;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RacingCarDao {

    private static JdbcTemplate jdbcTemplate;

    private static SimpleJdbcInsert insertRacingResult;
    private static SimpleJdbcInsert insertRacingRecord;

    public RacingCarDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertRacingRecord = new SimpleJdbcInsert(dataSource)
                .withTableName("RACING_RECORD")
                .usingGeneratedKeyColumns("id");

        this.insertRacingResult = new SimpleJdbcInsert(dataSource)
                .withTableName("RACING_RESULT")
                .usingGeneratedKeyColumns("id");
    }
    public static void setRacingRecord(List<Car> car) {
        for (Car racingCar : car) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", racingCar.getName());
            parameters.put("move", racingCar.getPosition());
            parameters.put("work_detail_tmst", LocalDateTime.now());
            insertRacingRecord.execute(parameters);
        }
    }

    public static RacingResult getRacingResult(RacingResult racingResult) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", racingResult.getCount());
        parameters.put("winners", racingResult.getWinners().toString());
        parameters.put("work_detail_tmst", LocalDateTime.now());
        insertRacingResult.execute(parameters);
        return racingResult;
    }


    //모든 실행결과
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public static List<RacingHistory> getGameHistory() {
        String sql = "SELECT TRIAL_COUNT, WINNERS FROM RACING_RESULT";

        return jdbcTemplate.query(sql
                , (resultSet, rowNum) -> new RacingHistory(
                        resultSet.getInt("TRIAL_COUNT")
                       , Collections.singletonList(resultSet.getString("WINNERS"))));


    }

}