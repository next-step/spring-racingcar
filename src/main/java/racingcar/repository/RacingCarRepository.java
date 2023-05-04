package racingcar.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.RaceResult;
import racingcar.domain.RacingCar;
import racingcar.mapper.RaceResultRowMapper;

@RequiredArgsConstructor
@Repository
public class RacingCarRepository {

  private final JdbcTemplate jdbcTemplate;

  public void insertRacingResult(RaceResult raceResult, LocalDateTime racingDate) {
    String winners = raceResult.getWinners();
    List<RacingCar> racingCars = raceResult.getRacingCars();
    String sql = "INSERT INTO RACING_GAME (WINNERS, RACING_DATE) VALUES (?, ?)";
    jdbcTemplate.update(sql, winners, racingDate);
    for (RacingCar racingCar : racingCars) {
      String name = racingCar.getName();
      int position = racingCar.getPosition();
      String sql2 = "INSERT INTO RACING_GAME_HISTORY (NAME, POSITION, RACING_DATE) VALUES (?, ?, ?)";
      jdbcTemplate.update(sql2, name, position, racingDate);
    }
  }


  public List<RacingCar> findAll() {
    String sql = "SELECT * FROM RACING_GAME_HISTORY";
    return jdbcTemplate.query(sql, (rs, rowNum) -> new RacingCar(rs.getString("NAME"), rs.getInt("POSITION")));
  }

}
