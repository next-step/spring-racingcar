package racingcar.dto;

import lombok.Getter;
import racingcar.domain.RacingCar;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RacingCarResponse {

    private final String name;
    private final int position;

    public RacingCarResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static RacingCarResponse of(RacingCar racingCar){
        return new RacingCarResponse(racingCar.getName(), racingCar.getPosition());
    }

    public static List<RacingCarResponse> listOf(List<RacingCar> racingCars){
        return racingCars.stream()
                .map(RacingCarResponse::of)
                .collect(Collectors.toList());
    }


}
