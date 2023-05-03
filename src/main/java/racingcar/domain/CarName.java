package racingcar.domain;

public class CarName {
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public CarName(String name){
        checkNameValidation(name);
        this.name = name;
    }

    String getName(){
        return name;
    }

    private void checkNameValidation(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

}
