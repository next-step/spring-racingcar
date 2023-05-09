package racingcar.domain.plays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PlaysPosition {
    private int play_result_id;
    private String name;
    private int position;
}
