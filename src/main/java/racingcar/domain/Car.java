package racingcar.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

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
        if (CarRandom.isMove())
            moveForward();
    }

    public void moveForward() {
        position++;
    }

}
