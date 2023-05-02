package racingcar.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.repository.PlayResultRepository;
import racingcar.domain.repository.RacingCarRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayResultDAO implements PlayResultRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertActor;
    private final RacingCarRepository racingCarRepository;

    public PlayResultDAO(DataSource dataSource,
                         RacingCarRepository racingCarRepository) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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

    @Override
    public List<PlayResult> findAll() {
        String sql = "select * from play_result";

        // playResult 조회
        List<PlayResult> playResults = this.namedParameterJdbcTemplate.query(sql, (resultSet, rowNum) -> {
            PlayResult playResult = PlayResult.builder()
                    .id(resultSet.getInt("id"))
                    .trialCount(resultSet.getInt("trial_count"))
                    .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                    .build();
            playResult.setWinnersFromString(resultSet.getString("winner"));
            return playResult;
        });

        // racingCar 조회
        List<Integer> ids = playResults.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<RacingCar> racingCarList = racingCarRepository.findByPlayResultId(ids);

        // playResult - racingCar 매핑
        for (PlayResult playResult : playResults) {
            List<RacingCar> listFilterByPlayResultId = racingCarList.stream()
                    .filter(r -> r.getPlayResultId() == playResult.getId())
                    .collect(Collectors.toList());

            playResult.setRacingCars(new RacingCars(listFilterByPlayResultId));
        }

        return playResults;
    }
}
