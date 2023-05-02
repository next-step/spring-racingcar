package racingcar.controller;

public class ApiCreateRacingPlayerResponse {

    String name;
    Integer position;

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public ApiCreateRacingPlayerResponse(String name, Integer position) {
        this.name = name;
        this.position = position;
    }
}
