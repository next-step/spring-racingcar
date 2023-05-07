package racingcar.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.repository.rowmapper.PlayResultRowMapper;

import java.io.IOException;
import java.util.List;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Repository
public class RacingResultRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper =  new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    private static final PlayResultRowMapper ROW_MAPPER = new PlayResultRowMapper();

    public RacingResultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGameResult(PlayResult playResult) {
        try {
            String carsToJson = objectMapper.writeValueAsString(playResult.getRacingCars());
            String sql = "insert into PLAY_RESULT (winners, trial_count, racing_cars) values (?, ?, ?)";
            jdbcTemplate.update(sql, playResult.getWinners(), playResult.getTrialCount(), carsToJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public PlayResult getResult() {
        String sql = "SELECT * FROM play_result order by id desc limit 1";


        return jdbcTemplate.queryForObject(sql, ROW_MAPPER);
    }


    public List<PlayResult> getResultAll() {
        String sql = "SELECT * FROM play_result order by id";


        return jdbcTemplate.query(sql, ROW_MAPPER);
    }


}
