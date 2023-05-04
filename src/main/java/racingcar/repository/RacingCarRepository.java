package racingcar.repository;

import static racingcar.domain.RaceResult.getWinnersString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.RaceResult;
import racingcar.domain.RacingCar;

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

  public List<RaceResult> findAll() {
    String sql = "SELECT * FROM RACING_GAME_HISTORY";
    Map<LocalDateTime, List<RacingCar>> resultMap = new LinkedHashMap<>();
    jdbcTemplate.query(sql, (rs, rowNum) -> {
      LocalDateTime racingDate = rs.getTimestamp("RACING_DATE").toLocalDateTime();
      RacingCar racingCar = new RacingCar(rs.getString("NAME"), rs.getInt("POSITION"));
      resultMap.computeIfAbsent(racingDate, k -> new ArrayList<>()).add(racingCar);
      return racingCar;
    });

    List<RaceResult> results = new ArrayList<>();
    for (Map.Entry<LocalDateTime, List<RacingCar>> entry : resultMap.entrySet()) {
      List<RacingCar> racingCars = entry.getValue();
      results.add(new RaceResult(getWinnersString(racingCars), racingCars));
    }
    return results;
  }

}
