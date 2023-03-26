package racingcar.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int position;

    @ManyToOne
    @JoinColumn(name = "play_result_id")
    private PlayResult playResult;

    @CreatedDate
    private LocalDateTime createAt;

    public Car() {
    }

    public Car(PlayResult playResult, String name) {
        this.playResult = playResult;
        this.name = name;
    }

    public Car(String name) {
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

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
            return false;
        Car car = (Car) obj;
        return id == car.id;
    }
}
