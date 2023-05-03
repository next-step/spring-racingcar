package racingcar.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import racingcar.jdbc.PlayCarResult;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class RacingCarResponse {
    private String name;
    private int position;

    public static List<RacingCarResponse> of (List<PlayCarResult> playCarResults) {
        List<RacingCarResponse> list = new ArrayList<>();
        for (PlayCarResult playCarResult : playCarResults) {
            list.add(new RacingCarResponse(playCarResult.getName(), playCarResult.getPosition()));
        }
        return list;
    }
}
