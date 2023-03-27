package racingcar.domain;

import java.util.Objects;

public class Car {

    private final String name;

    private final int moveOption;

    private int position;

    public Car(String name){
        this(name, 0);
    }

    public Car(String name, int position){
        vaildName(name);

        this.position = position;
        this.name = name;
        this.moveOption = 4;
    }

    private void vaildName(String name){
        if(name.length() > 5){
            throw new IllegalArgumentException("이름이 5자를 초과했음");
        }
    }

    public boolean move(int moveValue){
        boolean isMove = false;

        if(moveOption <= moveValue){
            position++;
            isMove = true;
        }

        return isMove;
    }

    public boolean equalsPosition(int position){
        return this.position == position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return name;
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