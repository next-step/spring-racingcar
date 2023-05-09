package racingcar.repository.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.domain.Car;
import racingcar.domain.GameHistory;
import racingcar.dto.GameHistoryResponseDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.repository.GameHistoryRepository;

@Repository
public class JdbcGameHistoryDao implements GameHistoryRepository {
	private SimpleJdbcInsert jdbcInsert;
	private NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcGameHistoryDao() {
	}

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
	public GameHistoryResponseDto findAllWithGameResults() {
		String sql =
			"SELECT gh.id, gh.play_result_id, gh.name, gh.position, gh.created_at, gr.winners, gr.trial_count " +
				"FROM game_history gh " +
				"JOIN game_result gr ON gh.play_result_id = gr.id " +
				"ORDER BY gh.play_result_id";
		return jdbcTemplate.query(sql, new GameHistoryWithGameResultExtractor());
	}

	private static class GameHistoryWithGameResultExtractor implements ResultSetExtractor<GameHistoryResponseDto> {
		@Override
		public GameHistoryResponseDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, List<Car>> carsByGameId = new LinkedHashMap<>();
			Map<Long, String> winnersByGameId = new HashMap<>();

			while (rs.next()) {
				long gameId = rs.getLong("play_result_id");
				String name = rs.getString("name");
				int position = rs.getInt("position");
				String winners = rs.getString("winners");

				Car car = new Car(name, position);
				carsByGameId.computeIfAbsent(gameId, k -> new ArrayList<>()).add(car);
				winnersByGameId.putIfAbsent(gameId, winners);
			}

			List<RacingCarResponseDto> histories = carsByGameId.entrySet().stream()
				.map(entry -> new RacingCarResponseDto(winnersByGameId.get(entry.getKey()), entry.getValue()))
				.collect(Collectors.toList());

			return new GameHistoryResponseDto(histories);
		}
	}
}

