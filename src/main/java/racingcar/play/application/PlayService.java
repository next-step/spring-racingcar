package racingcar.play.application;

import org.springframework.stereotype.Service;
import racingcar.play.api.dto.PlayRequest;
import racingcar.play.application.dto.PlayResultResponse;
import racingcar.play.domain.RacingCars;

@Service
public class PlayService {

    public PlayResultResponse playGame(PlayRequest playRequest) {
        RacingCars racingCars = playRequest.toRacingCars();
        racingCars.playRound(playRequest.getCount());
        return PlayResultResponse.from(racingCars);
    }
}
