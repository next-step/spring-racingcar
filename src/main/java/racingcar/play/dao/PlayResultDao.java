package racingcar.play.dao;


import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.play.domain.PlayResult;

@Repository
public class PlayResultDao {

    private final SimpleJdbcInsert jdbcInsert;

    public PlayResultDao(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("play_result")
            .usingGeneratedKeyColumns("id", "created_at");
    }

    public Long save(PlayResult playerResult) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(playerResult);
        return (Long) jdbcInsert.executeAndReturnKeyHolder(param)
            .getKeys()
            .get("id");
    }
}
