package racingcar.controller;

public class PlayInput {

    private String names;
    private Integer count;

    public PlayInput(String names, Integer count) {
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
