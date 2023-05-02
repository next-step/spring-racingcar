package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.RacingPlayer;

import javax.validation.Validator;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Objects;

@Repository
public class RacingPlayerRepositoryJDBC extends BaseRepository<RacingPlayer, Long> implements RacingPlayerRepository {

    @Autowired
    public RacingPlayerRepositoryJDBC(JdbcTemplate jdbcTemplate, Validator validator) {
        super(jdbcTemplate, validator);
    }

    @Override
    public RacingPlayer save(RacingPlayer entity) {
        super.validate(entity);
        if (entity.getId() == null) {
            return insert(entity);
        } else {
            throw new IllegalArgumentException("이미 저장된 데이터입니다.");
        }
    }

    @Override
    protected RacingPlayer insert(RacingPlayer entity) {
        // Insert a new record
        String insertSql = "INSERT INTO racing_players (racing_game_id, name, position, is_winner, created_date) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        entity.setCreatedDate(LocalDateTime.now());

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, entity.getRacingGameId());
                    ps.setString(2, entity.getName());
                    ps.setInt(3, entity.getPosition());
                    ps.setBoolean(4, entity.getWinner());
                    ps.setTimestamp(5, java.sql.Timestamp.valueOf(entity.getCreatedDate()));
                    return ps;
                }, keyHolder);

        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return entity;
    }
}
