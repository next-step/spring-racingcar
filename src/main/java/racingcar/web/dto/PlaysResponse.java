package racingcar.web.dto;

import lombok.*;
import racingcar.domain.dto.RacingCarDto;
import racingcar.domain.dto.RacingGameResultDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaysResponse {

    private List<String> winners;
    private List<RacingCarDto> racingCars;

    public static PlaysResponse from(RacingGameResultDto racingGameResultDto) {
        return PlaysResponse.builder()
                .winners(racingGameResultDto.getWinners())
                .racingCars(racingGameResultDto.getRacingCarDtos())
                .build();
    }

}
