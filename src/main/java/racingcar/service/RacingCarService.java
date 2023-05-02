package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.request.PlayRacingCarRequest;
import racingcar.controller.dto.response.PlayRacingCarResponse;
import racingcar.domain.RacingCarGame;
import racingcar.domain.RacingCars;
import racingcar.repository.PlayHistoryDao;
import racingcar.repository.PlayResultDao;

@Service
public class RacingCarService {

    private final PlayResultDao playResultDao;
    private final PlayHistoryDao playHistoryDao;

    public RacingCarService(PlayResultDao playResultDao, PlayHistoryDao playHistoryDao) {
        this.playResultDao = playResultDao;
        this.playHistoryDao = playHistoryDao;
    }

    public PlayRacingCarResponse play(PlayRacingCarRequest request) {
        RacingCars racingCars = RacingCars.of(request.getPlayerNames());

        RacingCarGame racingCarGame = RacingCarGame.of(racingCars, request.getCount());
        racingCarGame.play();

        String winners = racingCarGame.winners();

        Integer playResultId = playResultDao.save(winners, request.getCount());
        playHistoryDao.save(playResultId, racingCars);

        return PlayRacingCarResponse.of(winners, racingCars);
    }
}
