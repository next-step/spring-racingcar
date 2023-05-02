package racingcar.domain.plays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PlaysResult {
    private int id;
    private String winners;
}
