package racingcar.usecase.request;

import javax.validation.constraints.*;
import java.util.List;

public class PlayRacingGameRequest {

    @NotNull @Size(min = 1) private final List<@NotBlank String> nameList;
    @Positive private final int trialCount;

    public List<String> getNameList() {
        return nameList;
    }


    public int getTrialCount() {
        return trialCount;
    }

    public PlayRacingGameRequest(List<String> nameList, int trialCount) {
        this.nameList = nameList;
        this.trialCount = trialCount;
    }
}
