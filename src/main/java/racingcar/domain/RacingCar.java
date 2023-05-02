package racingcar.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RacingCar {
    private String name;
    private int position;

    public RacingCar() {
    }

    public RacingCar(String name) {
        this.name = name;
        this.position = 0;
    }

    public RacingCar(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public void move(int randomNumber) {
        if (randomNumber >= 4) {
            this.position++;
        }
    }
}