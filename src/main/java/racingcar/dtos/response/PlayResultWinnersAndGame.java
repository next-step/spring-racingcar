package racingcar.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayResultWinnersAndGame {
    private Long game;
    private String winners;
}
