package racingcar.entity;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
