package racingcar.entity;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public abstract boolean isNew();

}
