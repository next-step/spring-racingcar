package racingcar.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Car")
public class Car {
    @Id
    private String name;

    private int position;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime createAt;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void move() {
        move(CarRandom.isMove());
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
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
            return false;
        Car car = (Car) obj;
        return name.equals(car.getName());
    }

}
