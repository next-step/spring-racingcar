package racingcar.infra;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.GameResult;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGameRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class GameDao implements RacingGameRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public GameDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(GameResult gameResult) {
        int playResult = insertResult(gameResult.getTrialCount(), gameResult.getWinner());
        insertPlayResult(gameResult.getRacingCars(), playResult);
    }

    private int insertResult(int trialCount, String winner) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("trial_count", trialCount)
                .addValue("winners", winner)
                .addValue("created_at", LocalDateTime.now());

        return simpleJdbcInsert.executeAndReturnKey(params).intValue();
    }

    private void insertPlayResult(List<RacingCar> racingCars, int playResult) {
        jdbcTemplate.batchUpdate("INSERT INTO PLAYER_RESULT(play_id, name, position) VALUES ( ?, ?, ?) ",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, playResult);
                        ps.setString(2, racingCars.get(i).getName());
                        ps.setInt(3, racingCars.get(i).getPosition());
                    }

                    @Override
                    public int getBatchSize() {
                        return racingCars.size();
                    }
                }
        );
    }

}
