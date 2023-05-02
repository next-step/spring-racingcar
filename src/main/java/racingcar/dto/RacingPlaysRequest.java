package racingcar.dto;

import lombok.Getter;
import lombok.ToString;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */

@Getter
@ToString
public class RacingPlaysRequest {
    private String names;
    private int count;
}
