package racingcar.dto;

public class RacingInput {

    private String names;
    private Integer count;

    public RacingInput(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public Integer getCount(){
        return count;
    }
}