package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import racingcar.entity.RacingGame;

import javax.validation.Validator;

@Repository
public class RacingGameRepositoryJDBC extends BaseRepositoryJDBC<RacingGame, Long> implements RacingGameRepository {

    @Autowired
    public RacingGameRepositoryJDBC(JdbcTemplate jdbcTemplate, Validator validator) {
        super(jdbcTemplate, validator);
    }

    @Override
    protected RacingGame insert(RacingGame entity) {
        // Insert a new record
        String insertSql = "INSERT INTO racing_games (trial_count, created_date) VALUES (?, ?)";

        PreparedStatementSetter pss = ps -> {
            ps.setInt(1, entity.getTrialCount());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(entity.getCreatedDate()));
        };

        return super.insert(entity, insertSql, pss);
    }
}
