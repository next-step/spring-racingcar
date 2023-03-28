package racingcar.jdbctemplate;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class InsertDao {
    private static SimpleJdbcInsert insertActor;

    public InsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("racinghistory")
                .usingGeneratedKeyColumns("id");
    }

    public static void insertWithMap(Car car, int count, String winners) {
        long systemTime = System.currentTimeMillis();
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("trial_count", count);
        parameters.put("name", car.getName());
        parameters.put("position", car.getPosition());
        parameters.put("winners", winners);
        parameters.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(systemTime));
        insertActor.execute(parameters);
    }
}
