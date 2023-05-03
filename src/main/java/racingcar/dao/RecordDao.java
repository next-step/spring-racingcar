package racingcar.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.entity.Records;

@Repository
public class RecordDao {
	private final SimpleJdbcInsert insertActor;

	public RecordDao(DataSource dataSource) {
		this.insertActor = new SimpleJdbcInsert(dataSource)
			.withTableName("records")
			.usingGeneratedKeyColumns("id");
	}

	public Records insert(Cars cars, int count) throws JsonProcessingException {
		Date now = new Date();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("trial_count", count);

		String winnersJoin = cars.getWinners()
			.stream()
			.map(Car::getName)
			.collect(Collectors.joining(","));
		parameters.put("winners", winnersJoin);

		ObjectMapper mapper = new ObjectMapper();
		String carsJson = mapper.writeValueAsString(cars.getCars());
		parameters.put("racing_cars", carsJson);

		parameters.put("created_at", now);
		long id = insertActor.executeAndReturnKey(parameters).longValue();
		return new Records(id, count, winnersJoin, carsJson, now);
	}
}
