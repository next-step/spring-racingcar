package racingcar.dto;

import lombok.Getter;
import racingcar.domain.RacingCar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */

@Getter
public class RacingPlaysRequest {
    private String names;
    private int count;

    public List<RacingCar> getConvertRequestNameToCarList(){
        List<RacingCar> result = new ArrayList<>();
        Arrays.stream(names.split(",")).forEach(
                car -> result.add(new RacingCar(car))
        );
        return result;
    }
}
