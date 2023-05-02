package racingcar.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Repository
public class RacingResultRepository {
    private final JdbcTemplate jdbcTemplate;
    private ObjectMapper objectMapper =  new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    public RacingResultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGameResult(PlayResult playResult) {
        try {
            String carsToJson = objectMapper.writeValueAsString(playResult.getRacingCars());
            String sql = "insert into PLAY_RESULT (winners, trial_count, racing_cars) values (?, ?, ?)";
            int test = jdbcTemplate.update(sql, playResult.getWinners(), playResult.getTrialCount(), carsToJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public PlayResult getResults() {
        String sql = "SELECT * FROM play_result order by created_at desc limit 1";
        List<PlayResult> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String winners = rs.getString("winners");
            int trialCount = rs.getInt("trial_count");
            LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
            String jsonRacingCars = rs.getString("racing_cars");
            List<RacingCar> racingCars;

            try {
                racingCars = objectMapper.readValue(jsonRacingCars, new TypeReference<>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return new PlayResult(winners, trialCount, racingCars, createdAt);
        });
        return result.get(0);
    }
}
