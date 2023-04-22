package racingCar.domain;

public class RacingCar {

    private static final int MOVABLE_MIN_NUMBER = 4;

    private final String name;

    private int position;

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
        if (randomNumber >= MOVABLE_MIN_NUMBER) {
            this.position++;
        }
    }
}