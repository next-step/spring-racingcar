package racingcar.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlayHistoryDto {

    private String winners;
    private List<PlayResponseDto.RacingCar> racingCars;

    @Getter
    @AllArgsConstructor
    public static class RacingCar {

        private String name;
        private int position;

    }

    public PlayResponseDto toPlayResponseDto() {
        return new PlayResponseDto(winners, racingCars);
    }

}
