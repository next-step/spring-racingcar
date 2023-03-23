package racingcar;

import org.springframework.stereotype.Service;
import racingcar.model.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private RacingCarRepository racingCarRepository;

    public RacingCarService(RacingCarRepository racingCarRepository) {

        this.racingCarRepository = racingCarRepository;
    }

    public RacingResponse racingGame(String names, int count) {

        Racing racing = new Racing(names, count);

        racing.race();

        String winners = racing.getWinnerNames();

        List<CarResponse> carResponses = racing.getCarResponses();

        racingCarRepository.insertPlayResult(winners, count);

        int maxId = racingCarRepository.selectMaxIdOfPlayResult();

        for (Car car: racing.getCars()) {
            racingCarRepository.insertPlayHistory(maxId, car.getName(), car.getPosition());
        }

        return new RacingResponse(winners, carResponses);
    }

    public List<RacingResponse> getRacingGameHistory() {

        List<PlayResultDao> playResultDaos = racingCarRepository.selectListPlayResult();

        List<RacingResponse> racingResponses = new ArrayList<>();

        for (PlayResultDao playResultDao : playResultDaos) {
            List<PlayHistoryDao> playHistoryDaos = racingCarRepository.selectListPlayHistory(playResultDao.getId());

            // Set Response
            RacingResponse racingResponse = new RacingResponse(
                    playResultDao.getWinners(),
                    playHistoryDaos.stream()
                            .map(it -> new CarResponse(it.getName(), it.getPosition()))
                            .collect(Collectors.toUnmodifiableList())
            );

            racingResponses.add(racingResponse);
        }

        return racingResponses;
    }
}
