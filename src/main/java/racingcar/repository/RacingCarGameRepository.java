package racingcar.repository;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RacingCarGameRepository {
    private final SimpleJdbcInsert resultInsertActor;
    private final SimpleJdbcInsert detailResultInsertActor;

    public RacingCarGameRepository(DataSource dataSource) {
        this.resultInsertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");

        this.detailResultInsertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_DETAIL_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public long saveResult(RacingGame racingGame) {
        String winners = racingGame.getRacingWinners().stream().map(Car::toString).collect(Collectors.joining(", "));
        final int resultTableInsertColumnCount = 2;
        Map<String, Object> parameters = new HashMap<>(resultTableInsertColumnCount);

        parameters.put("TRIAL_COUNT", racingGame.getTrialCount());
        parameters.put("WINNERS", winners);
        parameters.put("CREATED_AT", LocalDateTime.now());
        return resultInsertActor.executeAndReturnKey(parameters).longValue();

    }

    public void saveDetailResult(long playId, RacingGame racingGame) {
        final int detailResultTableInsertColumnCount = 5;
        Map<String, Object> parameters = new HashMap<>(detailResultTableInsertColumnCount);
        List<Car> participationCars = racingGame.getParticipationCars();

        for (Car participationCar : participationCars) {
            parameters.put("PLAY_ID", playId);
            parameters.put("PLAYER", participationCar.getName());
            parameters.put("POSITION", participationCar.getPosition());
            parameters.put("IS_WINNER", racingGame.isWinner(participationCar));
            parameters.put("CREATED_AT", LocalDateTime.now());
            detailResultInsertActor.execute(parameters);
        }
    }
}
