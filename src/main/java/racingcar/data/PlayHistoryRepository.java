package racingcar.data;

import racingcar.data.entity.PlayHistory;

import java.util.List;

public interface PlayHistoryRepository {

    void insertHistory(List<PlayHistory> playHistories);

    List<PlayHistory> getPlayHistoriesByPlayResultId(long resultId);

}
