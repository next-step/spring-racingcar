package racingcar.infra;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.domain.PlayResultRepository;
import racingcar.infra.rowmaper.PlayResultRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlayResultDao extends NamedParameterJdbcDaoSupport implements PlayResultRepository {

    private static final String TABLE_NAME = "play_result";
    private static final String ID = "id";
    private static final String[] COLUMNS = {"winners", "trial_count"};
    private static final PlayResultRowMapper ROW_MAPPER = new PlayResultRowMapper();

    private final SimpleJdbcInsert simpleJdbcInsert;

    public PlayResultDao(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID)
                .usingColumns(COLUMNS);
    }

    @Override
    public int save(PlayResult playResult) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(playResult);
        Number key = simpleJdbcInsert.executeAndReturnKey(parameterSource);

        return key.intValue();
    }

    @Override
    public List<PlayResult> findAll() {
        final String query = "SELECT * FROM play_result";
        return getNamedParameterJdbcTemplate()
                .query(query, ROW_MAPPER);
    }

}
