package racingcar.domain.plays;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PlaysRepository {

    private final JdbcTemplate jdbcTemplate;
    private int id;

    public void insertPlayResult(String winners, int count) {
        String sql = "INSERT INTO PLAY_RESULT (WINNERS, COUNT) VALUES (?, ?)";
        jdbcTemplate.update(sql, winners, count);
        this.id++;
    }

    public void insertPlayPosition(String name, int position) {
        String sql = "INSERT INTO PLAY_POSITION (PLAY_RESULT_ID, NAME, POSITION) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, this.id, name, position);
    }

    public List findAll() {
        List<PlaysPosition> playsPositions =  jdbcTemplate.query("SELECT * FROM PLAY_POSITION", new RowMapper<PlaysPosition>() {
            @Override
            public PlaysPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
                PlaysPosition playsPosition = new PlaysPosition(
                        rs.getInt("play_result_id"),
                        rs.getString("name"),
                        rs.getInt("position")
                );
                return playsPosition;
            }
        });
        List<PlaysResult> playsResults = jdbcTemplate.query("SELECT * FROM PLAY_RESULT", new RowMapper<PlaysResult>() {
            @Override
            public PlaysResult mapRow(ResultSet rs, int rowNum) throws SQLException {
                PlaysResult playsResult = new PlaysResult(
                        rs.getInt("id"),
                        rs.getString("winners")
                );
                return playsResult;
            }
        });

        Map<Integer, List<PlaysPosition>> winnersMap = playsPositions.stream().collect(Collectors.groupingBy(PlaysPosition::getPlay_result_id));
        List<PlaysDTO.Response> responses = new ArrayList<>();
        for (PlaysResult playsResult : playsResults) {
            String winners = playsResult.getWinners();
            int id = playsResult.getId();
            List<PlaysPosition> positions = winnersMap.get(id);

            List<PlaysDTO.RacingCar> racingCars = positions.stream()
                    .map(position -> PlaysDTO.RacingCar.builder()
                            .name(position.getName())
                            .position(position.getPosition())
                            .build())
                    .collect(Collectors.toList());

            PlaysDTO.Response response = PlaysDTO.Response.builder()
                    .winners(winners)
                    .racingCars(racingCars)
                    .build();

            responses.add(response);
        }

        return responses;
    }
}
