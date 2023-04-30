package racingcar.presentation.dto;

public class GameStartDto {
    private String names;
    private Integer count;

    public GameStartDto(String names, Integer count) {
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
