package racingcar.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name = "PLAY_RESULT_SEQ_GENERATOR", sequenceName = "PLAY_SEQ", initialValue = 1, allocationSize = 1)
@Table(name = "PLAY_RESULT", uniqueConstraints = {
        @UniqueConstraint(name = "ID_UNIQUE", columnNames = { "ID" })
})
public class PlayResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAY_RESULT_SEQ_GENERATOR")
    private long id;

    @Column(name = "WINNERS", nullable = false, length = 100)
    private String winners;

    private LocalDateTime createdAt;

    public long getId() {
        return id;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public String getWinners() {
        return winners;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
