package racingcar.game.domain;

import java.util.List;

public interface PlayResultRepository {

    Long save(PlayResult playerResult);

    List<PlayResultEntity> findAll();
}
