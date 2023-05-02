package racingcar.domain;

public class RacingCarResult {

    private String name;

    private Integer position;

    private RacingCarResult(String name, Integer position) {
        this.name = name;
        this.position = position;
    }

    public static RacingCarResult of(RacingCar racingCar) {
        return new RacingCarResult(racingCar.getName(), racingCar.getPosition());
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }
}
