package racingcar.infra;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayHistory;
import racingcar.domain.PlayHistoryRepository;
import racingcar.infra.rowmaper.PlayHistoryRwoMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlayHistoryDao extends NamedParameterJdbcDaoSupport implements PlayHistoryRepository {

    private static final String TABLE_NAME = "player_history";
    private static final String ID = "id";
    private static final String[] COLUMNS = {"play_result_id", "name", "position"};
    private static final PlayHistoryRwoMapper ROW_MAPPER = new PlayHistoryRwoMapper();

    private final SimpleJdbcInsert simpleJdbcInsert;

    public PlayHistoryDao(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID)
                .usingColumns(COLUMNS);
    }

    @Override
    public void save(List<PlayHistory> playHistories) {
        MapSqlParameterSource[] params = createParam(playHistories);
        simpleJdbcInsert.executeBatch(params);
    }

    private MapSqlParameterSource[] createParam(List<PlayHistory> playHistories) {
        return playHistories.stream()
                .map(it -> new MapSqlParameterSource()
                        .addValue("play_result_id", it.getPlayResultId())
                        .addValue("name", it.getName())
                        .addValue("position", it.getPosition()))
                .toArray(MapSqlParameterSource[]::new);
    }

    @Override
    public List<PlayHistory> findAll() {
        final String query = String.format("SELECT * FROM %s", TABLE_NAME);
        return getNamedParameterJdbcTemplate()
                .query(query, ROW_MAPPER);
    }

}
