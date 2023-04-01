package racingcar.domain;

import java.util.Objects;
import java.util.Random;

public class Car implements Comparable<Car> {

    private static final int MAX_NAME_LENGTH = 5;
    private static final int INITIAL_LOCATION = 0;
    private static final int CHECK_MAX_RANDOM_NUMBER = 10;
    private static final int MOVABLE_MIN_NUMBER = 4;

    private final String name;
    private int position;

    public Car(String name) {
        this(name, INITIAL_LOCATION);
    }

    public Car(String name, int position) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없다.");
        }
        this.name = name;
        this.position = position;
    }

    public void move(Random random) {
        if (random.nextInt(CHECK_MAX_RANDOM_NUMBER) >= MOVABLE_MIN_NUMBER) {
            position++;
        }
    }

    public void printLocation() {
        String stringBuilder = name + " : " + "-".repeat(Math.max(INITIAL_LOCATION, position));
        System.out.println(stringBuilder);
    }

    public boolean isEqualLocation(Car car) {
        return car.position == this.position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(Car c) {
        return this.position - c.position;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return position == car.position && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
