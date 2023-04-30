package racingcar.web.dto;

import lombok.*;
import racingcar.domain.RacingCar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaysRequest {
    private String names;
    private Integer count;

    @Override
    public String toString() {
        return "names : " + names + " count : " + count;
    }

    public List<RacingCar> getRacingCars() {
        return Arrays.stream(this.getNameArray())
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }

    private String[] getNameArray() {
        return names.split(",");
    }
}
