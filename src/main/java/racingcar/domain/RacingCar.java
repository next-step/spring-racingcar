package racingcar.domain;

public class RacingCar {

    private static final int MOVE_CONDITION = 4;

    private final String name;
    private int position = 0;

    public RacingCar(String name) {
        this.name = name;
    }

    public void move(int number) {
        if (number >= MOVE_CONDITION) {
            this.position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

}
