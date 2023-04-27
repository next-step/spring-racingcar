package racingcar.infra;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResultRepository;

import javax.sql.DataSource;
import java.util.HashMap;

@Repository
public class PlayResultDao implements PlayResultRepository {

    private static final String TABLE_NAME = "play_result";
    private static final String ID = "id";
    private static final String[] COLUMNS = {"winners", "trial_count"};

    private final SimpleJdbcInsert simpleJdbcInsert;

    public PlayResultDao(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID)
                .usingColumns(COLUMNS);
    }

    @Override
    public void save(String winners, int trialCount) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("winners", winners);
        param.put("trial_count", trialCount);

        simpleJdbcInsert.execute(param);
    }

}
