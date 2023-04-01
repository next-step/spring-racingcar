package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RacingCarApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(RacingCarApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {
		jdbcTemplate.execute("DROP TABLE PLAY_RESULT IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE PLAY_RESULT (" +
						"    id          INT         NOT NULL AUTO_INCREMENT," +
						"    trial_count INT         NOT NULL," +
						"    name        VARCHAR(50) NOT NULL," +
						"    position    INT         NOT NULL," +
						"    winners     VARCHAR(50) NOT NULL," +
						"    created_at  DATETIME    NOT NULL default current_timestamp," +
						"    PRIMARY KEY (id)" +
						")"
				);
	}
}
