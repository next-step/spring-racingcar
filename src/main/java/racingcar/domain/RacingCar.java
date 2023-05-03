package racingcar.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RacingCar {
    private static final int MOVE_MINIMUM_NUMBER = 4;
    private int id;
    private int playResultId;
    private String name;
    private int position;
    private boolean isWinner;
    private LocalDateTime createdAt;

    public RacingCar(String name) {
        this.name = name;
        this.position = 0;
    }

    public void move(int randomNumber) {
        if (randomNumber >= MOVE_MINIMUM_NUMBER) {
            this.position++;
        }
    }

    public void setPlayResult(int playResultId) {
        this.playResultId = playResultId;
    }

    public void setWin() {
        this.isWinner = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacingCar racingCar = (RacingCar) o;
        return position == racingCar.position && Objects.equals(name, racingCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}