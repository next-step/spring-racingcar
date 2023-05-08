package racingcar.jdbc.repository;

import racingcar.jdbc.PlayCarResult;
import racingcar.jdbc.PlayResult;
import racingcar.jdbc.PlayRacingDao;

import java.util.List;

public class ConsoleRepository implements PlayRacingDao {
    @Override
    public void insert(PlayResult playResult) {
    }
    @Override
    public String findWinnerById(Long id) {
        return null;
    }

    @Override
    public List<PlayCarResult> getPlayCarResult(Long play_result_id) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
