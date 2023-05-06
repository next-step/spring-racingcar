package racingcar.domain;

import lombok.Getter;
import racingcar.dto.RacingCarResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author minsukim on 2023/05/06
 * @project jwp-racingcar
 * @description
 */
@Getter
public class RacingCars {
    private List<RacingCar> racingCars = new ArrayList<>();

    public RacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public String getWinnersToString(){
        int maxPosition = 0;
        List<RacingCarResponse> winners = new ArrayList<>();
        for (RacingCar racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(new RacingCarResponse(racingCar.getName(), racingCar.getPosition()));
            }
        }
        return winners.stream()
                .map(RacingCarResponse::getName)
                .collect(Collectors.joining(", "));
    }
}
