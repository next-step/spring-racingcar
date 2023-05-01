package racingcar.play.application;

import org.springframework.stereotype.Service;
import racingcar.play.api.dto.PlayRequest;
import racingcar.play.application.dto.PlayResultResponse;
import racingcar.play.dao.PlayResultDao;
import racingcar.play.dao.PlayerDao;
import racingcar.play.domain.PlayResult;
import racingcar.play.domain.Player;
import racingcar.play.domain.RacingCars;

@Service
public class PlayService {

    private final PlayResultDao playResultDao;
    private final PlayerDao playerDao;

    public PlayService(PlayResultDao playResultDao, PlayerDao playerDao) {
        this.playResultDao = playResultDao;
        this.playerDao = playerDao;
    }

    public PlayResultResponse playGame(PlayRequest playRequest) {
        RacingCars racingCars = playRequest.toRacingCars();
        racingCars.playRound(playRequest.getCount());
        Long playResultSavedId = playResultDao.save(PlayResult.of(racingCars.getWinners(), playRequest.getCount()));
        savePlayer(racingCars, playResultSavedId);
        return PlayResultResponse.from(racingCars);
    }

    private void savePlayer(RacingCars racingCars, Long playResultSavedId) {
        racingCars.getRacingCars()
                .forEach(racingCar -> playerDao.save(Player.of(playResultSavedId, racingCar)));
    }
}
