package racingcar.domain.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.domain.GameHistory;
import racingcar.repository.GameHistoryRepository;

@Repository
public class GameHistoryDao implements GameHistoryRepository {
	private static final String TABLE_NAME = "game_history";
	private static final String ID_COLUMN = "id";
	private static final String DATE_COLUMN = "created_at";

	private final SimpleJdbcInsert simpleJdbcInsert;

	public GameHistoryDao(DataSource dataSource) {
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName(TABLE_NAME)
			.usingGeneratedKeyColumns(ID_COLUMN, DATE_COLUMN);
	}

	@Override
	public void saveAll(List<GameHistory> gameHistories) {
		SqlParameterSource[] params = createGameHistoryParams(gameHistories);
		simpleJdbcInsert.executeBatch(params);
	}

	private SqlParameterSource[] createGameHistoryParams(List<GameHistory> gameHistories) {
		return gameHistories.stream()
			.map(BeanPropertySqlParameterSource::new)
			.toArray(SqlParameterSource[]::new);
	}
}
