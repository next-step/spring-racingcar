package racingcar.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingCarResponse;

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
    private ObjectMapper objectMapper =  new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

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

    public PlayResult getResults() {
        String sql = "SELECT * FROM play_result order by id desc limit 1";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

            String jsonRacingCars = rs.getString("racing_cars").replaceAll("\\\\", "");
            jsonRacingCars = jsonRacingCars.substring(1, jsonRacingCars.length()-1);
            List<RacingCar> racingCars = null;
            try {
                racingCars = objectMapper.readValue(jsonRacingCars, new TypeReference<>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException("racingcars json -> List<RacingCar> 변환실패");
            }
            return PlayResult.builder()
                    .trialCount(rs.getInt("trial_count"))
                    .racingCars(RacingCarResponse.listOf(racingCars))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .build();
        });
    }

}
