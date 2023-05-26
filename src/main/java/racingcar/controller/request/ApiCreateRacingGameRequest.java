package racingcar.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ApiCreateRacingGameRequest {

    private final String names;
    private final Integer count;

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }

    public ApiCreateRacingGameRequest(String names, Integer count) {
        this.names = names;
        this.count = count;
    }
}
