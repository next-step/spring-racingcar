package racingcar.domain.repository;

import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;

@Repository
public interface PlayResultRepository {

    void save(PlayResult playResult);
}
