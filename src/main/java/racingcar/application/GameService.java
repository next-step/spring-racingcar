package racingcar.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingCars;
import racingcar.domain.Winners;
import racingcar.infra.GameDao;

@Service
public class GameService {

    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Transactional
    public void saveGameResult(RacingCars racingCars, Winners winners) {
        gameDao.savePlayResult(racingCars, winners);
    }
}
