package racingcar.domain.plays;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class PlaysDTO {

    @Getter
    public static class Request {
        private String names;
        private Integer count;
    }

    @Getter
    @Builder
    public static class Response {
        private String winners;
        private List<RacingCar> racingCars;
    }

    @Getter
    @Builder
    public static class RacingCar {
        private String name;
        private Integer position;
    }
}
