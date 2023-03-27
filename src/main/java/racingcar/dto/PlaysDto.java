package racingcar.dto;

public class PlaysDto {

    private String names;
    private Integer count;

    public PlaysDto() {
    }

    public PlaysDto(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }

}
