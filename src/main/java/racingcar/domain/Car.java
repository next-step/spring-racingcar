package racingcar.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cars")
public class Car {
    @Id
    private String name;

    private int position;

    private LocalDateTime createAt;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void move() {
        if (CarRandom.isMove())
            moveForward();
    }

    public void moveForward() {
        position++;
    }
}
