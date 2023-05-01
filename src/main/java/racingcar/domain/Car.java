package racingcar.domain;

import lombok.Getter;

@Getter
public class Car {
    private Position position;
    private final CarName name;

    Car(String name) {
        this.name = new CarName(name);
        position = new Position();
    }

    public String getName() {
        return this.name.getName();
    }

    public Integer getPosition() {
        return this.position.getValue();
    }

    void go(MoveState moveState) {
        if (moveState.isMovable()) {
            this.position = position.getAddedPosition();
        }
    }

    boolean isSameNumber(int number) {
        return this.position.equals(new Position(number));
    }
}
