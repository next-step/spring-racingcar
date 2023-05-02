package racingcar.jdbc.dao.insert;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.jdbc.PlayResult;

import javax.sql.DataSource;

@Repository
public class PlayResultInsertDao {

    private SimpleJdbcInsert insertActor;

    public PlayResultInsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public PlayResult insert(PlayResult playResult) {
        BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(playResult);
        long id = insertActor.executeAndReturnKey(parameters).longValue();
        return new PlayResult(id, playResult.getTrialCount(), playResult.getWinners(), playResult.getCreatedAt());
    }
}
