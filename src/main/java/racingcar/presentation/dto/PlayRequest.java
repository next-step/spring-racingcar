package racingcar.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.List;

public class PlayRequest {

    private String names;
    private Integer count;

    private PlayRequest() {
    }

    public PlayRequest(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    @JsonIgnore
    public List<String> getPlayers() {
        return Arrays.asList(names.split(","));
    }

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }
}
