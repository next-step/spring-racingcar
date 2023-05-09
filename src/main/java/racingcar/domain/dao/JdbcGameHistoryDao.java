package racingcar.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.domain.GameHistory;
import racingcar.repository.GameHistoryRepository;

@Repository
public class JdbcGameHistoryDao implements GameHistoryRepository {
	private final SimpleJdbcInsert jdbcInsert;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcGameHistoryDao(DataSource dataSource) {
		this.jdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("game_history")
			.usingGeneratedKeyColumns("id", "created_at");
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void saveAll(List<GameHistory> gameHistories) {
		BeanPropertySqlParameterSource[] parameterSources = gameHistories.stream()
			.map(BeanPropertySqlParameterSource::new)
			.toArray(BeanPropertySqlParameterSource[]::new);
		jdbcInsert.executeBatch(parameterSources);
	}

	@Override
	public List<GameHistory> findAll() {
		String sql = "SELECT id, play_result_id, name, position, created_at FROM game_history";
		return jdbcTemplate.query(sql, new GameHistoryRowMapper());
	}

	private static class GameHistoryRowMapper implements RowMapper<GameHistory> {
		@Override
		public GameHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
			return GameHistory.builder()
				.id(rs.getInt("id"))
				.playResultId(rs.getInt("play_result_id"))
				.name(rs.getString("name"))
				.position(rs.getInt("position"))
				.createdAt(rs.getTimestamp("created_at").toLocalDateTime())
				.build();
		}
	}
}
