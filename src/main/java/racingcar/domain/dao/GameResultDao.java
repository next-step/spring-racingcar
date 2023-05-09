package racingcar.domain.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.domain.GameResult;
import racingcar.repository.GameResultRepository;

@Repository
public class GameResultDao implements GameResultRepository {
	private final SimpleJdbcInsert simpleJdbcInsert;

	public GameResultDao(DataSource dataSource) {
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("game_result")
			.usingGeneratedKeyColumns("id", "created_at");
	}

	@Override
	public int save(GameResult gameResult) {
		SqlParameterSource parameterSource = createSqlParameterSource(gameResult);
		Object key = simpleJdbcInsert.executeAndReturnKeyHolder(parameterSource).getKeys().get("id");
		return (int)key;
	}

	private SqlParameterSource createSqlParameterSource(GameResult gameResult) {
		return new BeanPropertySqlParameterSource(gameResult);
	}
}
