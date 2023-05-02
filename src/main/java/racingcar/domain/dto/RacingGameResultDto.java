package racingcar.domain.dto;

import lombok.*;
import racingcar.domain.PlayResult;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RacingGameResultDto {
    private List<String> winners;
    private List<RacingCarDto> racingCarDtos;

    public static RacingGameResultDto from(PlayResult playResult) {
        return new RacingGameResultDto(playResult.getWinners(),
                playResult.getRacingCars().getRacingCarDtos());
    }

}
