package racingcar.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final PlayWinnerDAO playWinnerDAO;
    private final PlayAllResultDAO playAllResultDAO;

    public PlayResultDAO(JdbcTemplate jdbcTemplate, DataSource dataSource,
                         PlayWinnerDAO playWinnerDAO, PlayAllResultDAO playAllResultDAO) {
        this.jdbcTemplate = jdbcTemplate;
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
        this.playWinnerDAO = playWinnerDAO;
        this.playAllResultDAO = playAllResultDAO;
    }

    public int insert(int trialCount) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", trialCount);
        parameters.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public void save(PlayResult playResult) {
        int newId = this.insert(playResult.getTrialCount());

        RacingGameResult gameResult = playResult.getRacingGameResult();
        for(String winner : gameResult.getWinners()) {
            playWinnerDAO.insert(newId, winner);
        }
        for(RacingCarDto racingCar : gameResult.getRacingCarDtos()) {
            playAllResultDAO.insert(newId, racingCar.getName(),
                    racingCar.getPosition());
        }
    }

}
