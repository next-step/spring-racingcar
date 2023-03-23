package racingcar;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.model.PlayHistoryDao;
import racingcar.model.PlayResultDao;

import java.util.List;

@Repository
public class RacingCarRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String ID = "id";
    private static final String WINNERS = "winners";
    private static final String TRIAL_COUNT = "trial_count";
    private static final String CREATED_AT = "created_at";
    private static final String TURN = "turn";
    private static final String NAME = "name";
    private static final String POSITION = "position";




    public RacingCarRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPlayResult(String winners, int count) {
        String resultSql = "INSERT INTO PLAY_RESULT(winners, trial_count) VALUES (?,?)";
        jdbcTemplate.update(resultSql, winners, count);
    }

    public void insertPlayHistory(int turn, String name, int position) {
        String historySql = "INSERT INTO PLAY_HISTORY(turn, name, position) VALUES (?,?,?)";
        jdbcTemplate.update(historySql, turn, name, position);
    }

    public int selectMaxIdOfPlayResult() {
        String maxIdSql = "SELECT MAX(ID) FROM PLAY_RESULT";
        return jdbcTemplate.queryForObject(maxIdSql, Integer.class);
    }

    public List<PlayResultDao> selectListPlayResult() {
        String winnersSql = "SELECT * FROM PLAY_RESULT";
        return jdbcTemplate.query(
                winnersSql, (rs, rowNum) -> {
                    PlayResultDao playResultDao = new PlayResultDao(
                            rs.getInt(ID),
                            rs.getString(WINNERS),
                            rs.getInt(TRIAL_COUNT),
                            rs.getDate(CREATED_AT)
                    );
                    return playResultDao;
                });
    }

    public List<PlayHistoryDao> selectListPlayHistory(int turn) {
        String playHistorySql = "SELECT * FROM PLAY_HISTORY WHERE TURN = ?";

        return jdbcTemplate.query(
                playHistorySql, (rs, rowNum) -> {
                    PlayHistoryDao playHistoryDao = new PlayHistoryDao(
                            rs.getInt(ID),
                            rs.getInt(TURN),
                            rs.getString(NAME),
                            rs.getInt(POSITION),
                            rs.getDate(CREATED_AT)
                    );
                    return playHistoryDao;
                }, turn);
    }
}
