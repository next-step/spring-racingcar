package racingcar.jdbc;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlayCarResult {
    private long id;
    private String name;
    private int position;
    private LocalDateTime createdAt;

    public PlayCarResult(String name, int position, LocalDateTime createdAt) {
        this.name = name;
        this.position = position;
        this.createdAt = createdAt;
    }

    public PlayCarResult(long id, String name, int position, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.createdAt = createdAt;
    }
}
