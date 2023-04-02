package racingcar.dto;

public class RacingGameDTO {
    private String names;
    private Integer count;

    public RacingGameDTO() {
    }

    public RacingGameDTO(String names, Integer count) {
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
