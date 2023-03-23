package racingcar.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RacingRequest {

    @NotEmpty(message = "자동차 이름 입력 필요")
    private String names;

    @NotNull(message = "시도 횟수 입력 필요")
    @Min(value = 1, message = "시도 횟수는 1 이상")
    private int count;

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
