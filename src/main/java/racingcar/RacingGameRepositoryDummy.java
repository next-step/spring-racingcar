package racingcar;

import org.springframework.stereotype.Repository;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RacingPlayerRepository;

import java.util.List;

public class RacingGameRepositoryDummy implements RacingGameRepository {

    @Override
    public RacingGame save(RacingGame entity) {
        return null;
    }

    @Override
    public List<RacingGame> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
