package racingcar.domain;

import lombok.Builder;
import lombok.Getter;
import racingcar.dto.RacingCarResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Getter
public class PlayResult {
    private String winners;
    private int trialCount;
    private List<RacingCarResponse> racingCars;
    private LocalDateTime createdAt;

    @Builder
    public PlayResult(int trialCount, List<RacingCarResponse> racingCars, LocalDateTime createdAt) {
        this.winners = getWinnersStringFromList(getWinners(racingCars));
        this.trialCount = trialCount;
        this.racingCars = racingCars;
        this.createdAt = createdAt;
    }

    private List<RacingCarResponse> getWinners(List<RacingCarResponse> racingCars){
        int maxPosition = 0;
        List<RacingCarResponse> winners = new ArrayList<>();
        for (RacingCarResponse racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(new RacingCarResponse(racingCar.getName(), racingCar.getPosition()));
            }
        }
        return winners;
    }

    private String getWinnersStringFromList(List<RacingCarResponse> racingCars){
        return racingCars.stream()
                .map(RacingCarResponse::getName)
                .collect(Collectors.joining(", "));
    }

}
