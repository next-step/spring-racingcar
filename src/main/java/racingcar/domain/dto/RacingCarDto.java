package racingcar.domain.dto;

import lombok.*;
import racingcar.domain.RacingCar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RacingCarDto {

    private String name;
    private Integer position;

    public static RacingCarDto from(RacingCar racingCar) {
        return RacingCarDto.builder()
                .name(racingCar.getName())
                .position(racingCar.getPosition())
                .build();
    }
}
