package racingcar.domain;

public class RacingCar {

    private final String name;
    private int position = 0;

    public RacingCar(String name) {
        this.name = name;
    }

    public void move(int distance) {
        this.position = position + distance;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

}
