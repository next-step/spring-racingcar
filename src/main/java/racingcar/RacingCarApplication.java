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

		jdbcTemplate.execute("DROP TABLE racinghistory IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE racinghistory(" +
				"id SERIAL, " +
				"round INTEGER" +
				"trial_count INTEGER default 0, " +
				"name VARCHAR(255), " +
				"position INTEGER, " +
				"winners VARCHAR(255), " +
				"date VARCHAR(255))");
	}
}


