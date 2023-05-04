package racingcar.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PlayResponseDto {

    private String winners;
    private List<RacingCar> racingCars;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RacingCar {

        private String name;
        private int position;

    }

}
