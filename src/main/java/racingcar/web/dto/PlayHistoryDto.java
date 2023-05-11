package racingcar.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import racingcar.entity.PlayHistoryDetail;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PlayHistoryDto {

    private String winners;
    private List<PlayHistoryDto.RacingCar> racingCars;

    @Getter
    @AllArgsConstructor
    public static class RacingCar {

        private String name;
        private int position;

        public RacingCar(PlayHistoryDetail playHistoryDetail) {
            this.name = playHistoryDetail.getName();
            this.position = playHistoryDetail.getPosition();
        }
    }

    public PlayResponseDto toPlayResponseDto() {
        List<PlayResponseDto.RacingCar> playResponseDtoRacingCars = this.racingCars.stream()
                .map(racingCar -> new PlayResponseDto.RacingCar(racingCar.name, racingCar.position))
                .collect(Collectors.toList());

        return new PlayResponseDto(winners, playResponseDtoRacingCars);
    }

}
