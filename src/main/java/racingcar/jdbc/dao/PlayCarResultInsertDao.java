package racingcar.jdbc.dao;


import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.jdbc.PlayCarResult;

import javax.sql.DataSource;

@Repository
public class PlayCarResultInsertDao {

    private SimpleJdbcInsert insertActor;

    public PlayCarResultInsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_CAR_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public PlayCarResult insertWithBeanPropertySqlParameterSource(PlayCarResult playCarResult) {
        BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(playCarResult);
        long id = insertActor.executeAndReturnKey(parameters).longValue();
        return new PlayCarResult(id, playCarResult.getName(), playCarResult.getPosition(), playCarResult.getCreatedAt());
    }
}
