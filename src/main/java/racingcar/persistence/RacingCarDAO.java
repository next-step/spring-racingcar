package racingcar.persistence;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.RacingCar;
import racingcar.domain.repository.RacingCarRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RacingCarDAO implements RacingCarRepository {
    private SimpleJdbcInsert insertActor;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RacingCarDAO(DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("RACING_CAR")
                .usingGeneratedKeyColumns("id");
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public int insert(RacingCar racingCar) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("play_result_id", racingCar.getPlayResultId());
        parameters.put("player", racingCar.getName());
        parameters.put("position", racingCar.getPosition());
        parameters.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public List<RacingCar> findByPlayResultId(List<Integer> playResultIds) {
        if (playResultIds.isEmpty()) {
            return new ArrayList<>();
        }

        String sql = "select * from racing_car where play_result_id in (:playResultIds)";
        MapSqlParameterSource parameters = new MapSqlParameterSource("playResultIds", playResultIds);
        return namedParameterJdbcTemplate.query(sql, parameters, (rs, rowNum) -> {
            return RacingCar.builder()
                    .id(rs.getInt("id"))
                    .playResultId(rs.getInt("play_result_id"))
                    .name(rs.getString("player"))
                    .position(rs.getInt("position"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .build();
        });
    }
}
