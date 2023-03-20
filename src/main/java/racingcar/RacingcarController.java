package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.model.Car;
import racingcar.model.RacingRequest;
import racingcar.model.RacingResponse;

import java.util.stream.Collectors;

@RestController
public class RacingcarController {

    private JdbcTemplate jdbcTemplate;

    public RacingcarController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> racingGame(@RequestBody RacingRequest racingRequest) {
        Racing racing = new Racing(racingRequest.getNames(), racingRequest.getCount());

        while (!racing.isEnd()) {
            racing.startRacing();
        }

        String winners = racing.getWinners().stream()
                .map(Car::getName).collect(Collectors.joining(", "));
        String resultSql = "INSERT INTO PLAY_RESULT(winners, trial_count) VALUES (?,?)";
        jdbcTemplate.update(resultSql, winners, racingRequest.getCount());

        String maxIdSql = "SELECT MAX(ID) FROM PLAY_RESULT";
        int maxId = jdbcTemplate.queryForObject(maxIdSql, Integer.class);

        String historySql = "INSERT INTO PLAY_HISTORY(turn, name, position) VALUES (?,?,?)";
        for (Car car: racing.getCars()) {
            jdbcTemplate.update(historySql, maxId, car.getName(), car.getPosition());
        }

        return ResponseEntity.ok(new RacingResponse(winners, racing.getCars()));
    }
}
