package racingcar.application;

import org.springframework.stereotype.Service;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomMoveStrategy;
import racingcar.presentation.dto.PlayRequest;
import racingcar.presentation.dto.PlayResponse;

@Service
public class GameService {

    public PlayResponse play(PlayRequest request) {
        RacingCars racingCars = RacingCars.of(request.getPlayers());
        int playCount = request.getCount();

        RacingGame racingGame = RacingGame.of(racingCars);
        racingGame.play(playCount, new RandomMoveStrategy());
        String winners = racingGame.getWinners();

        return PlayResponse.of(winners, racingCars);
    }

}
