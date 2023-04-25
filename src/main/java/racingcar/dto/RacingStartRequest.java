package racingcar.dto;


import lombok.Getter;

@Getter
public class RacingStartRequest {
    private String names;
    private int count;

    public String getNames() {
        return this.names;
    }

    public int getCount() {
        return this.count;
    }
}
