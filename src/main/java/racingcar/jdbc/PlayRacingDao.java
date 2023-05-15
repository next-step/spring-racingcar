package racingcar.jdbc;

import java.util.List;

public interface PlayRacingDao {

    void insert(PlayResult playResult);

    String findWinnerById(Long id);

    List<PlayCarResult> getPlayCarResult(Long id);

    int count();

}
