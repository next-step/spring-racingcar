package racingcar.api.response;

import racingcar.racing.RacingCar;

public class Car {

    private String name;

    private int position;

    public Car(RacingCar racingCar) {
        this.name = racingCar.getDriver().getName();
        this.position = racingCar.getPosition().getPoistion();
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }
}
