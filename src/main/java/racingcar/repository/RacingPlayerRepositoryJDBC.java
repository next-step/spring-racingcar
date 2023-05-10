package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import racingcar.entity.RacingPlayer;

import javax.validation.Validator;

@Repository
public class RacingPlayerRepositoryJDBC extends BaseRepositoryJDBC<RacingPlayer, Long> implements RacingPlayerRepository {

    @Autowired
    public RacingPlayerRepositoryJDBC(JdbcTemplate jdbcTemplate, Validator validator) {
        super(jdbcTemplate, validator);
    }

    @Override
    protected RacingPlayer insert(RacingPlayer entity) {
        // Insert a new record
        String insertSql = "INSERT INTO racing_players (racing_game_id, name, position, is_winner, created_date) VALUES (?, ?, ?, ?, ?)";

        PreparedStatementSetter pss = ps -> {
            ps.setLong(1, entity.getRacingGameId());
            ps.setString(2, entity.getName());
            ps.setInt(3, entity.getPosition());
            ps.setBoolean(4, entity.isWinner());
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(entity.getCreatedDate()));
        };

        return super.insert(entity, insertSql, pss);
    }

    @Override
    public void deleteAll() {
        super.deleteAll("racing_players");
    }
}
