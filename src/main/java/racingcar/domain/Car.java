package racingcar.domain;

import racingcar.behavior.MovingStrategy;
import racingcar.util.ErrorMessage;

public class Car {
    static final int DEFAULT_DISTANCE = 0;
    private static final int MAX_INPUT_NAME_VALUE = 5;
    private static final String BLANK = " ";

    private final String name;
    private int position;

    public Car(String name) {
        this.position = DEFAULT_DISTANCE;
        this.name = validateNamesOfCars(name);
    }

    public static String validateNamesOfCars(String name) {
        if (isNullOrEmpty(name) || isFiveLetterWords(name)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NAME_ERROR);
        }
        return name;
    }

    static boolean isNullOrEmpty(String name) {
        return name.trim().isEmpty() || name.equals(BLANK);
    }

    static boolean isFiveLetterWords(String carName) {
        return carName.trim().length() > MAX_INPUT_NAME_VALUE;
    }

    public void move(final MovingStrategy movingStrategy) {
        if (movingStrategy.isMovable()) {
            position++;
        }
    }

    public int getPosition() {
        return this.position;
    }

    public boolean hasSamePosition(int position) {
        return this.position == position;
    }


    public String getName() {
		return name;
	}
}
