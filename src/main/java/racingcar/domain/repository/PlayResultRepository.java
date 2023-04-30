package racingcar.domain.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.entity.PlayResult;
import racingcar.domain.entity.RacingCar;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PlayResultRepository {

    private JdbcTemplate jdbcTemplate;
    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    public PlayResultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(PlayResult result) {
        try {
            String jsonRacingCars = objectMapper.writeValueAsString(result.getRacingCars());
            String sql = "INSERT INTO play_result (winners, trial_count, racing_cars) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, result.getWinners(), result.getTrialCount(), jsonRacingCars);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PlayResult> getAllPlayResults() {
        String sql = "SELECT * FROM play_result";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String winners = rs.getString("winners");
            int trialCount = rs.getInt("trial_count");
            LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
            String jsonRacingCars = rs.getString("racing_cars");
            List<RacingCar> racingCars;
            try {
                racingCars = objectMapper.readValue(jsonRacingCars, new TypeReference<List<RacingCar>>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return new PlayResult(id, winners, trialCount, racingCars, createdAt);
        });
    }
}
