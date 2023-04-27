package racingcar.infra;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayHistoryRepository;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlayHistoryDao implements PlayHistoryRepository {

    private static final String TABLE_NAME = "player_history";
    private static final String ID = "id";
    private static final String[] COLUMNS = {"name", "position"};

    private final SimpleJdbcInsert simpleJdbcInsert;

    public PlayHistoryDao(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID)
                .usingColumns(COLUMNS);
    }

    @Override
    public void save(RacingCars racingCars) {
        MapSqlParameterSource[] paramsList = createParam(racingCars);
        simpleJdbcInsert.executeBatch(paramsList);
    }

    private MapSqlParameterSource[] createParam(RacingCars racingCars) {
        List<RacingCar> cars = racingCars.getRacingCars();

        return cars.stream()
                .map(it -> new MapSqlParameterSource()
                        .addValue("name", it.getName())
                        .addValue("position", it.getPosition()))
                .toArray(MapSqlParameterSource[]::new);
    }

}
