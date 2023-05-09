package racingcar.repository.rowmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.dto.RacingCarResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class PlayResultRowMapper implements RowMapper<PlayResult> {

    private final ObjectMapper objectMapper =  new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    @Override
    public PlayResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<RacingCar> racingCars = getRacingCarListToJson(rs.getString("racing_cars").replaceAll("\\\\", ""));
        return PlayResult.builder()
                .winners(new RacingCars(racingCars).getWinnersToString())
                .trialCount(rs.getInt("trial_count"))
                .racingCars(RacingCarResponse.listOf(racingCars))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }

    private List<RacingCar> getRacingCarListToJson(String jsonString){
        List<RacingCar> racingCars = null;
        try {
            jsonString = jsonString.substring(1, jsonString.length()-1);
            racingCars = objectMapper.readValue(jsonString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("racingcars json -> List<RacingCar> 변환실패");
        }

        return racingCars;
    }

}
