package racingcar.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.domain.GameResult;
import racingcar.repository.GameResultRepository;

@Repository
public class JdbcGameResultDao implements GameResultRepository {
	private final SimpleJdbcInsert jdbcInsert;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcGameResultDao(DataSource dataSource) {
		this.jdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("game_result")
			.usingGeneratedKeyColumns("id", "created_at");
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public long save(GameResult gameResult) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(gameResult);
		return (long) jdbcInsert.executeAndReturnKeyHolder(parameterSource)
			.getKeys()
			.get("id");
	}

	@Override
	public List<GameResult> findAll() {
		String sql = "SELECT id, winners, trial_count, created_at FROM game_result";
		return jdbcTemplate.query(sql, new GameResultRowMapper());
	}

	private static class GameResultRowMapper implements RowMapper<GameResult> {
		@Override
		public GameResult mapRow(ResultSet rs, int rowNum) throws SQLException {
			return GameResult.builder()
				.id(rs.getLong("id"))
				.winners(rs.getString("winners"))
				.trialCount(rs.getInt("trial_count"))
				.createAt(rs.getTimestamp("created_at").toLocalDateTime())
				.build();
		}
	}
}
