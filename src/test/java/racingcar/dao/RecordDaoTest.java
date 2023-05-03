package racingcar.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import racingcar.domain.Cars;
import racingcar.entity.Records;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RecordDaoTest {
	@Autowired
	private RecordDao recordDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final List<String> CAR_NAMES = Arrays.asList("AAA", "BBB","CCC");
	private final int COUNT = 10;

	@BeforeEach
	void setUp() {
		jdbcTemplate.execute("TRUNCATE TABLE records");
	}

	@Test
	@DisplayName("게임 결과 insert 성공")
	void insert() throws JsonProcessingException {
		Records records = recordDao.insert(new Cars(CAR_NAMES), COUNT);
		assertThat(records.getId()).isEqualTo(1);
		assertThat(records.getWinners()).contains(CAR_NAMES);
		assertThat(records.getTrialCount()).isEqualTo(COUNT);
		assertThat(records.getCreatedAt()).isInThePast();
	}
}
