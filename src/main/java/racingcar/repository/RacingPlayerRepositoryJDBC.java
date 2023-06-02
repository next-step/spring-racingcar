package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;

import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;

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
    public List<RacingPlayer> findAll() {
        return super.findAll("racing_players", ((rs, rowNum) -> {
            long id = rs.getLong("id");
            long racingGameId = rs.getLong("racing_game_id");
            String name = rs.getString("name");
            int position = rs.getInt("position");
            boolean isWinner = rs.getBoolean("is_winner");
            LocalDateTime createdDate = rs.getTimestamp("created_date").toLocalDateTime();
            return RacingPlayer.MappingFactory.generate(id, name, position, isWinner, racingGameId, createdDate);
        }));
    }

    @Override
    public void deleteAll() {
        super.deleteAll("racing_players");
    }
}
