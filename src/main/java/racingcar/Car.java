package racingcar;

public class Car implements Comparable<Car> {
    private static final int LIMIT_NAME_LENGTH = 5;     // 자동차 이름 최대 글자수 제한값
    private static final String LIMIT_NUMBER_OF_CHARACTERS_EXCEPTION_MESSAGE = "자동차 이름은 5글자를 초과할 수 없습니다.";

    private final String name;
    private final Position position;

    public Car(final String name) {
        this(name, new Position());
    }

    public Car(final String name, final Position position) {
        if (name.length() > LIMIT_NAME_LENGTH) {
            throw new IllegalArgumentException(LIMIT_NUMBER_OF_CHARACTERS_EXCEPTION_MESSAGE);
        }
        this.name = name;
        this.position = position;
    }

    public void move(boolean moveable) {
        if (moveable) {
            position.moveForward();
        }
    }

    @Override
    public int compareTo(Car car) {
        return position.compareTo(car.position) * -1;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position.getPosition();
    }
}
