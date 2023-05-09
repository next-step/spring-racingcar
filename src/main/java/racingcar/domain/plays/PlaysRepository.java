package racingcar.domain.plays;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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

    public long insertPlayResult(String winners, int count) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO PLAY_RESULT (WINNERS, COUNT) VALUES (?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, winners);
            ps.setInt(2, count);
            return ps;
        }, keyHolder);

        List<Map<String, Object>> keys = keyHolder.getKeyList();

        if (!keys.isEmpty()) {
            Map<String, Object> keyEntry = keys.get(0);
            int generatedId = (Integer) keyEntry.get("ID");
            return generatedId;
        }

        throw new RuntimeException("No generated keys were returned");
    }

    public void insertPlayPosition(long id, String name, int position) {
        String sql = "INSERT INTO PLAY_POSITION (PLAY_RESULT_ID, NAME, POSITION) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id, name, position);
    }

    public List<PlaysDTO.Response> findAll() {
        List<PlaysResult> playsResults = jdbcTemplate.query("SELECT * FROM PLAY_RESULT", (rs, rowNum) -> new PlaysResult(rs.getInt("id"), rs.getString("winners")));
        Map<Integer, List<PlaysPosition>> playsPositions = jdbcTemplate.query("SELECT * FROM PLAY_POSITION", (rs, rowNum) -> new PlaysPosition(
                rs.getInt("play_result_id"),
                rs.getString("name"),
                rs.getInt("position")
        )).stream().collect(Collectors.groupingBy(PlaysPosition::getPlay_result_id));
        List<PlaysDTO.Response> responses = new ArrayList<>();
        for (PlaysResult playsResult : playsResults) {
            int id = playsResult.getId();
            List<PlaysPosition> positions = playsPositions.get(id);

            List<PlaysDTO.RacingCar> racingCars = positions.stream()
                    .map(position -> PlaysDTO.RacingCar.builder()
                            .name(position.getName())
                            .position(position.getPosition())
                            .build())
                    .collect(Collectors.toList());

            PlaysDTO.Response response = PlaysDTO.Response.builder()
                    .winners(playsResult.getWinners())
                    .racingCars(racingCars)
                    .build();

            responses.add(response);
        }

        return responses;
    }
}
