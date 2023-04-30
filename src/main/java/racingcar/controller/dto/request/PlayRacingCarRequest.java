package racingcar.controller.dto.request;

import java.util.List;

public class PlayRacingCarRequest {

    private String names;

    private Integer count;

    public PlayRacingCarRequest(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }

    public List<String> getPlayerNames() {
        return List.of(names.split(","));
    }
}
