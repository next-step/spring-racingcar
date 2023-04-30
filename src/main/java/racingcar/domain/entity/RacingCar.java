package racingcar.domain.entity;

public class RacingCar {

    private static final int CONDITION_TO_MOVE = 4;

    private String name;
    private int position;

    public RacingCar() {
    }

    public RacingCar(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }

    public void move(int randomNumber) {
        if (randomNumber >= CONDITION_TO_MOVE) {
            this.position++;
        }
    }
}
