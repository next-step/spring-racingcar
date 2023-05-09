package racingcar.repository;

import lombok.AllArgsConstructor;
import racingcar.dto.RacingCarRoundResult;

import java.util.LinkedList;
import java.util.List;

public class InMemoryRoundHistoryRepository implements BaseRoundHistoryRepository {

  @AllArgsConstructor
  private class RoundHistory {
    private int id;
    private int gameId;
    private int round;
    private String name;
    private int position;
  }

  private List<RoundHistory> roundHistories = new LinkedList<>();
  private int sequence = 0;

  public void save(int gameId, List<RacingCarRoundResult> roundResults) {
    roundResults.forEach(
        result ->
            roundHistories.add(
                new RoundHistory(
                    ++sequence,
                    gameId,
                    result.getRound(),
                    result.getCarName(),
                    result.getPosition())));
  }
}
