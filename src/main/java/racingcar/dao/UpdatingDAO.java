package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.repository.PlayResult;

@Repository
public class UpdatingDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(PlayResult playResult) {
        String sql = "insert into PLAY_RESULT (round, trial_count, name, position, winners) values(?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql
                , playResult.getRound()
                , playResult.getTrialCount()
                , playResult.getName()
                , playResult.getPosition()
                , playResult.getWinners());
    }
}
