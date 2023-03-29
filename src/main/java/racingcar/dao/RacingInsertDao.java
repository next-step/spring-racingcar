package racingcar.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class RacingInsertDao {
    private static SimpleJdbcInsert simpleJdbcInsert;

    public RacingInsertDao(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public static void insert(int trialCount, List<String> winnerList, Car car) {
        SqlParameterSource params = null;
        long time = System.currentTimeMillis();
        params = new MapSqlParameterSource()
                .addValue("trial_count", trialCount)
                .addValue("name", car.getName())
                .addValue("position", car.getPosition())
                .addValue("winners", winnerList)
                .addValue("created_at", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
        simpleJdbcInsert.execute(params);
    }
}
