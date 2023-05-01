package racingcar.data;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.RacingCars;
import racingcar.presentation.dto.GamePlayHistory;
import racingcar.presentation.dto.PlayHistory;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RacingRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public RacingRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public long insertGameResult(RacingCars racingCars) {
        if (racingCars.getWinners().isEmpty()) {
            throw new DataIntegrityViolationException("winners 가 비어있습니다.");
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("winners", racingCars.getWinners());
        parameters.put("created_at", LocalDateTime.now());
        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    public List<GamePlayHistory> getGameResults() {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select r.id as playResultId, " +
                "       winners as winners, " +
                "       h.id as historyId, " +
                "       h.play_result_id as playResultId, " +
                "       h.player_name as playerName, " +
                "       h.position as position " +
                "from PLAY_RESULT r join PLAY_HISTORY h " +
                "on r.id = h.play_result_id " +
                "order by r.created_at desc";

        Map<PlayHistory.PlayResult, List<PlayHistory>> queryResult = jdbcTemplate.query(sql, (rs, rowNum) -> {
            PlayHistory history = new PlayHistory();
            history.setHistoryId(rs.getLong("historyId"));
            history.setPosition(rs.getLong("position"));
            history.setPlayerName(rs.getString("playerName"));

            PlayHistory.PlayResult playResult = history.new PlayResult();
            playResult.setPlayResultId(rs.getLong("playResultId"));
            playResult.setWinners(rs.getString("winners"));
            history.setPlayResult(playResult);

            return history;
        }).stream().collect(Collectors.groupingBy(PlayHistory::getPlayResult));

        List<GamePlayHistory> gamePlayHistories = new ArrayList<>();

        for (Map.Entry<PlayHistory.PlayResult, List<PlayHistory>> entry : queryResult.entrySet()) {
            PlayHistory.PlayResult playResult = entry.getKey();
            List<PlayHistory> playHistories = entry.getValue();

            GamePlayHistory gamePlayHistory = new GamePlayHistory();

            String winners = playResult.getWinners();
            List<GamePlayHistory.RacingCar> racingCarList = playHistories.stream()
                    .map(player -> gamePlayHistory.new RacingCar(player.getPlayerName(), player.getPosition()))
                    .collect(Collectors.toList());

            gamePlayHistory.setWinners(winners);
            gamePlayHistory.setRacingCars(racingCarList);
            gamePlayHistories.add(gamePlayHistory);
        }
        return gamePlayHistories;
    }
}
