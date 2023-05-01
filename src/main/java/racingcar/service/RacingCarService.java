package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.request.PlayRacingCarRequest;
import racingcar.controller.dto.response.PlayRacingCarResponse;
import racingcar.domain.RacingCarGame;
import racingcar.domain.RacingCars;
import racingcar.repository.PlayResultRepository;

@Service
public class RacingCarService {

    private final PlayResultRepository playResultRepository;

    public RacingCarService(PlayResultRepository playResultRepository) {
        this.playResultRepository = playResultRepository;
    }

    public PlayRacingCarResponse play(PlayRacingCarRequest request) {
        RacingCars racingCars = RacingCars.of(request.getPlayerNames());

        RacingCarGame racingCarGame = RacingCarGame.of(racingCars, request.getCount());
        racingCarGame.play();

        String winners = racingCarGame.winners();

        playResultRepository.save(winners);

        return PlayRacingCarResponse.of(winners, racingCars);
    }
}
