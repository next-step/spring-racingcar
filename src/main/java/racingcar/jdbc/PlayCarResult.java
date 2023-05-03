package racingcar.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlayCarResult {
    private long id;
    private String name;
    private int position;
    private long play_result_id;
    private LocalDateTime createdAt;

    public PlayCarResult(String name, int position, long play_result_id, LocalDateTime createdAt) {
        this.name = name;
        this.position = position;
        this.play_result_id = play_result_id;
        this.createdAt = createdAt;
    }

    public PlayCarResult(String name, int position) {
        this.name = name;
        this.position = position;
    }
}
