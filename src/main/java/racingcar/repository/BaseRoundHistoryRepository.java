package racingcar.repository;

import racingcar.dto.RacingCarRoundResult;

import java.util.List;

public interface BaseRoundHistoryRepository {
  void save(int gameId, List<RacingCarRoundResult> roundResults);
}
