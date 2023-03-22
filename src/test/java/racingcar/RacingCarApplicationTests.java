package racingcar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.model.PlayHistoryDao;
import racingcar.model.PlayResultDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RacingCarApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void selectPlayResultTest() {
		String winnersSql = "SELECT * FROM PLAY_RESULT";
		List<PlayResultDao> playResultDaos = jdbcTemplate.query(
				winnersSql, (rs, rowNum) -> {
					PlayResultDao playResultDao = new PlayResultDao(
							rs.getInt("id"),
							rs.getString("winners"),
							rs.getInt("trial_count"),
							rs.getDate("created_at")
					);
					return playResultDao;
				});

		assertThat(playResultDaos.size()).isEqualTo(2);
	}

	@Test
	void selectPlayHistoryTest() {
		String playHistorySql = "SELECT * FROM PLAY_HISTORY WHERE TURN = ?";
		List<PlayHistoryDao> playHistoryDaos = jdbcTemplate.query(
				playHistorySql, (rs, rowNum) -> {
					PlayHistoryDao playHistoryDao = new PlayHistoryDao(
							rs.getInt("id"),
							rs.getInt("turn"),
							rs.getString("name"),
							rs.getInt("position"),
							rs.getDate("created_at")
					);
					return playHistoryDao;
				}, 1);

		assertThat(playHistoryDaos.size()).isEqualTo(2);
	}

	@Test
	void contextLoads() {
	}

}
