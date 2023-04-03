package racingcar.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingHistory;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InsertDao {
    private static SimpleJdbcInsert insertActor;

    public InsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("racinghistory")
                .usingGeneratedKeyColumns("id");
    }

    public static RacingCar insertWithMap(RacingCar racingCar, int count) {
        long systemTime = System.currentTimeMillis();
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("trial_count", count);
        parameters.put("name", racingCar.getName());
        parameters.put("position", racingCar.getPosition());
        parameters.put("winners", "senna");
        parameters.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(systemTime));
        Long id = insertActor.executeAndReturnKey(parameters).longValue();
        return new RacingCar(id, racingCar.getName());
    }
}
