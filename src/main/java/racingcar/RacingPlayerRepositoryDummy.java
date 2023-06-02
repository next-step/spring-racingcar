package racingcar;

import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RacingPlayerRepository;

import java.util.List;

public class RacingPlayerRepositoryDummy implements RacingPlayerRepository {

    @Override
    public RacingPlayer save(RacingPlayer entity) {
        return null;
    }

    @Override
    public List<RacingPlayer> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
