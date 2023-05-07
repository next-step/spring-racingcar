package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.dto.request.PlayRacingCarRequest;
import racingcar.controller.dto.response.PlayRacingCarResponse;
import racingcar.domain.*;
import racingcar.repository.PlayHistoryDao;
import racingcar.repository.PlayResultDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public List<PlayRacingCarResponse> playResult() {
        List<PlayResult> playResults =   playResultDao.findAll();
        List<PlayHistory> playHistories = playHistoryDao.findByHistoryById(
                playResults.stream()
                        .map(PlayResult::getId).collect(Collectors.toList())
        );

        return playResults.stream()
                .map(playResult -> {
                    List<RacingCar> racingCars = playHistories.stream()
                            .filter(history -> history.getPlayResultId().equals(playResult.getId()))
                            .map(history -> new RacingCar(history.getName(), history.getPosition()))
                            .collect(Collectors.toList());

                    return PlayRacingCarResponse.of(playResult.getWinners(), RacingCars.of(racingCars.stream()
                            .map(RacingCar::getName)
                            .collect(Collectors.toList())));
                })
                .collect(Collectors.toList());
    }
}
