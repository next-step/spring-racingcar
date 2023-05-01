package racingcar.domain;

import java.util.List;

public interface PlayResultRepository {

    int save(PlayResult playResult);

    List<PlayResult> findAll();

}
