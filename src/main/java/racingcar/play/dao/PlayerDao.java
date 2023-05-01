package racingcar.play.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.play.domain.Player;

@Repository
public class PlayerDao {

    private final SimpleJdbcInsert jdbcInsert;

    public PlayerDao(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("player")
            .usingGeneratedKeyColumns("id", "created_at");
    }

    public Long save(Player player) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(player);
        return (Long) jdbcInsert.executeAndReturnKeyHolder(param)
            .getKeys()
            .get("id");
    }
}
