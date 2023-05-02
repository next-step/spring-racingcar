package racingcar.web.dto;

import lombok.*;
import racingcar.domain.RacingCar;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private Integer count;

    @Override
    public String toString() {
        return "names : " + names + " count : " + count;
    }
}
