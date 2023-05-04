package racingcar.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import racingcar.domain.RaceResult;
import racingcar.domain.RacingCar;

public class RaceResultRowMapper implements RowMapper<RaceResult> {

  @Override
  public RaceResult mapRow(ResultSet rs, int rowNum) throws SQLException {
    String winners = rs.getString("WINNERS");
    List<RacingCar> racingCars = new ArrayList<>();
    String[] winnersArray = winners.split(",");
    for (String winner : winnersArray) {
      racingCars.add(new RacingCar(winner.trim(), rs.getInt("POSITION")));
    }
    return new RaceResult(winners, racingCars);
  }
}
