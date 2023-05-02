package racingcar.domain.repository;

import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;

public interface PlayResultRepository {

    void insert(PlayResult playResult);
}
