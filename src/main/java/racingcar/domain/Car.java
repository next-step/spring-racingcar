package racingcar.domain;

import racingcar.domain.dto.PlayResultDto;
import racingcar.domain.strategy.MovingStrategy;

import java.util.Objects;

public class Car {

    private Position position;
    private final Name name;

    public Car(String name) {
        this(0, name);
    }

    public Car(int position, String name) {
        this.position = new Position(position);
        this.name = new Name(name);
    }

    public void move(MovingStrategy movingStrategy) {
        if (movingStrategy.shouldMove()) {
            position = position.move();
        }
    }

    public PlayResultDto getPlayResult() {
        return new PlayResultDto(position, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(position, car.position) && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, name);
    }
}
