package racingcar.web.dto;

import lombok.*;
import racingcar.domain.RacingCar;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaysRequest {

    @NotEmpty
    private String names;
    @NotNull
    @Min(1)
    private Integer count;

    @Override
    public String toString() {
        return "names : " + names + " count : " + count;
    }
}
