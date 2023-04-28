package racingcar.domain;

import java.util.List;

public interface PlayResultRepository {

    void save(List<String> winners, int trialCount);

}
