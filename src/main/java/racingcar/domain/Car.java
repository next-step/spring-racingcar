package racingcar.domain;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import org.hibernate.Hibernate;

import lombok.Getter;

@Getter
@Embeddable
public class Car {
    private String name;

    private int position;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void move() {
        move(CarMoveUtil.isMove());
    }

    public void move(boolean bool) {
        if (bool == true)
            moveForward();
    }

    private void moveForward() {
        position++;
    }

}
