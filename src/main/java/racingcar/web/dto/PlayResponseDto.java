package racingcar.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import racingcar.domain.dto.PlayResultDto;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlayResponseDto {

    private String winners;
    private List<RacingCar> racingCars;

    @Getter
    @AllArgsConstructor
    public static class RacingCar {

        private String name;
        private int position;

        public RacingCar(PlayResultDto playResultDto) {
            this.name = playResultDto.getNameValue();
            this.position = playResultDto.getPositionValue();
        }
    }

}
