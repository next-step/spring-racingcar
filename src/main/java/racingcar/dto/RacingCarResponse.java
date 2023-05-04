package racingcar.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import racingcar.domain.RacingCar;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RacingCarResponse {

    private String name;
    private int position;

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
