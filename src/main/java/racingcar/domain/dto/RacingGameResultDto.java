package racingcar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RacingGameResultDto {
    private List<String> winners;
    private List<RacingCarDto> racingCarDtos;

}
