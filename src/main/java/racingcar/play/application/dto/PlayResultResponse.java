package racingcar.play.application.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import racingcar.play.domain.RacingCar;
import racingcar.play.domain.RacingCars;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlayResultResponse {

    private String winners;
    private List<RacingCar> racingCars;

    public static PlayResultResponse from(RacingCars racingCars) {
        return new PlayResultResponse(racingCars.getWinners(), racingCars.getRacingCars());
    }
}
