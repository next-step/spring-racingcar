package racingcar.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RacingRequest {
    private String names;
    private int count;
}
