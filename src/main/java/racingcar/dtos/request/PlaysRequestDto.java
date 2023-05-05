package racingcar.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaysRequestDto {
    private String names;
    private Integer trialCount;

}
