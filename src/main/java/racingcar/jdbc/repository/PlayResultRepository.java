package racingcar.jdbc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.RacingCar;
import racingcar.jdbc.PlayCarResult;
import racingcar.jdbc.PlayResult;
import racingcar.jdbc.PlayRacingDao;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PlayResultRepository implements PlayRacingDao {

    private SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;
    private final PlayCarResultRepository playCarResultRepository;

    public PlayResultRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, PlayCarResultRepository playCarResultRepository) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("play_id");
        this.jdbcTemplate = jdbcTemplate;
        this.playCarResultRepository = playCarResultRepository;
    }

    @Override
    public void insert(PlayResult playResult) {
        BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(playResult);
        long id = insertActor.executeAndReturnKey(parameters).longValue();
        for (RacingCar racingCar : playResult.getRacingCars()) {
            playCarResultRepository.insert(new PlayCarResult(racingCar.getName(), racingCar.getPosition(), id, LocalDateTime.now()));
        }
    }

    @Override
    public String findWinnerById(Long id) {
        String sql = "select winners from play_result where play_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> {
                    PlayResult playResult = new PlayResult(
                            resultSet.getString("winners")
                    );
                    return playResult.getWinners();
                }, id);
    }

    @Override
    public List<PlayCarResult> getPlayCarResult(Long id) {
        return playCarResultRepository.findByPlayResultId(id);
    }

    @Override
    public int count() {
        String sql = "select count(*) from play_result";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
