package racingcar.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
public class Cars {
    @Id
    private long id;

    @Embedded
    private List<Car> cars = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "play_result_id")
    private PlayResult playResult;

    @CreatedDate
    private LocalDateTime createAt;

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
        Cars cars = (Cars) obj;
        return id == cars.id;
    }
}
