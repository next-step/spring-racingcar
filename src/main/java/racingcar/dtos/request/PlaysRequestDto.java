package racingcar.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaysRequestDto {
    @NotNull(message = "차량이름이 입력되지 않았습니다.")
    private String names;

    @NotNull(message = "시도할 횟수가 입력되지 않았습니다.")
    private Integer trialCount;

}
