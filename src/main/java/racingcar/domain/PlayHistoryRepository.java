package racingcar.domain;

import java.util.List;

public interface PlayHistoryRepository {

    void save(List<PlayHistory> playHistories);

}
