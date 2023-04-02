package racingcar.domain;

import java.io.Serializable;

public class Car implements Serializable {
    private static final int FORWARD_NUMBER = 4; //4이상일때 이동
    private final String name;
    private int position;

    public Car(String name) {
        checkNameLength(name);
        this.name = name;
        this.position = 0;
    }

    private void checkNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("차이름은 5글자 미만이여야 합니다.");
        }
    }
    public String getName() {
        return this.name;
    }
    public int getPosition() {
        return this.position;
    }
    public void move(int randomNumber) {
        if (randomNumber >= FORWARD_NUMBER) {
            this.position++;
        }
    }



}