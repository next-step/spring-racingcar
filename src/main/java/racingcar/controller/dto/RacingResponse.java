package racingcar.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RacingResponse {
    private String winners;
    private List<RacingCarResponse> racingCars;
}
