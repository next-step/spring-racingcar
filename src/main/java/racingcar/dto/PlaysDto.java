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

    public void setNames(String names) {
        this.names = names;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
