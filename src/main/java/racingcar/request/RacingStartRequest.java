package racingcar.request;


import lombok.Getter;
import racingcar.reponse.RacingResultResponse;

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
