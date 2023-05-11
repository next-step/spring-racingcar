package racingcar.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import racingcar.jdbc.PlayCarResult;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class RacingCarResponse {
    private String name;
    private int position;

    public static List<RacingCarResponse> of (List<PlayCarResult> playCarResults) {
        return playCarResults.stream()
                .map(playCarResult -> new RacingCarResponse(playCarResult.getName(), playCarResult.getPosition())).collect(Collectors.toList());
    }
}
