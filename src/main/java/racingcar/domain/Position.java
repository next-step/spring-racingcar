package racingcar.domain;

import java.util.Objects;

public class Position {
    private final int value;

    Position() {
        this.value = 0;
    }

    Position(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    Position getAddedPosition() {
        return new Position(this.value + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
