package racingcar.game.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.game.domain.Player;

@Repository
public class PlayerDao {

    private final SimpleJdbcInsert jdbcInsert;

    public PlayerDao(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("player")
            .usingGeneratedKeyColumns("id", "created_at");
    }

    public void saveAll(List<Player> players) {
        BeanPropertySqlParameterSource[] param = players.stream()
            .map(BeanPropertySqlParameterSource::new)
            .toArray(BeanPropertySqlParameterSource[]::new);
        jdbcInsert.executeBatch(param);
    }
}
