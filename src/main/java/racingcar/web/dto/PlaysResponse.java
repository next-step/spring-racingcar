package racingcar.web.dto;

import lombok.*;
import racingcar.domain.dto.RacingCarDto;
import racingcar.domain.dto.RacingGameResult;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaysResponse {

    private List<String> winners;
    private List<RacingCarDto> racingCars;

    public static PlaysResponse from(RacingGameResult racingGameResult) {
        return PlaysResponse.builder()
                .winners(racingGameResult.getWinners())
                .racingCars(racingGameResult.getRacingCarDtos())
                .build();
    }

}
