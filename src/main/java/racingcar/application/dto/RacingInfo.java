package racingcar.application.dto;

import racingcar.domain.RacingCar;

public class RacingInfo {

    private final String name;
    private final int position;

    public RacingInfo(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public static RacingInfo from(RacingCar racingCar) {
        return new RacingInfo(racingCar.getName(), racingCar.getPosition());
    }
}
