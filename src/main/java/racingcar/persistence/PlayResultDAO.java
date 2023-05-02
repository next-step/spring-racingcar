package racingcar.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.domain.dto.RacingCarDto;
import racingcar.domain.dto.RacingGameResult;
import racingcar.domain.repository.PlayResultRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;

@Repository
public class PlayResultDAO implements PlayResultRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public PlayResultDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(PlayResult playResult) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", playResult.getTrialCount());
        parameters.put("winner", playResult.getWinner());
        parameters.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(parameters).intValue();
    }
}
