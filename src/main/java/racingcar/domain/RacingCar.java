package racingcar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RacingCar {
    private String name;
    private int position;

  public RacingCar(String name) {
        this.name = name;
        this.position = 0;
    }

    public void move(int randomNumber) {
        if (randomNumber >= 4) {
            this.position++;
        }
    }
}