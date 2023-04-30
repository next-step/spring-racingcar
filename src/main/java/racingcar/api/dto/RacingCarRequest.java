package racingcar.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RacingCarRequest {

    @NotBlank
    private String names;

    @NotNull
    @Min(1)
    private Integer count;

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }
}

