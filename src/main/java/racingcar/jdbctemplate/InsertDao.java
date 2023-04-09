package racingcar.jdbctemplate;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class InsertDao {
    private final SimpleJdbcInsert insertActor;

    public InsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("racing_history")
                .usingGeneratedKeyColumns("id");
    }

    public void insertWithMap(Car car, int count, String winners) {
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("trial_count", count);
        parameters.put("name", car.getName());
        parameters.put("position", car.getPosition());
        parameters.put("winners", winners);
        this.insertActor.execute(parameters);
    }
}
