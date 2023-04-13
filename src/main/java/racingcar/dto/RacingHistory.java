package racingcar.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;



public class RacingHistory implements Serializable {

    Integer trial_count = 0;
    List<String> winners = null;


    public RacingHistory(Integer trial_count, List<String> winners) {
        this.trial_count = trial_count;
        this.winners = winners;
    }

    public Integer getTrialCount() {
        return trial_count;
    }

    public List<String> getWinners() {
        return winners;
    }

}
