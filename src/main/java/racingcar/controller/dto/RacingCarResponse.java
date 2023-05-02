package racingcar.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RacingCarResponse {
    private String name;
    private int position;
}
