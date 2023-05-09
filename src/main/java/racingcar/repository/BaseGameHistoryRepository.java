package racingcar.repository;

import racingcar.dto.GameHistory;

import java.util.List;

public interface BaseGameHistoryRepository {
  Integer save(String names, int count);

  void updateWinners(Integer gameId, String winners);

  List<GameHistory> findAllWithHistory();
}
