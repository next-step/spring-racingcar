package racingcar.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.domain.repository.PlayResultRepository;
import racingcar.domain.repository.RacingCarRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;

@Repository
public class PlayResultDAO implements PlayResultRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    private final RacingCarRepository racingCarRepository;

    public PlayResultDAO(JdbcTemplate jdbcTemplate, DataSource dataSource,
                         RacingCarRepository racingCarRepository) {
        this.jdbcTemplate = jdbcTemplate;
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
        this.racingCarRepository = racingCarRepository;
    }

    public void insert(PlayResult playResult) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", playResult.getTrialCount());
        parameters.put("winner", playResult.getWinnersAsString());
        parameters.put("created_at", LocalDateTime.now());
        int newId = insertActor.executeAndReturnKey(parameters).intValue();

        for (RacingCar racingCar : playResult.getRacingCars().getRacingCars()) {
            racingCar.setPlayResult(newId);
            racingCarRepository.insert(racingCar);
        }

    }
}
