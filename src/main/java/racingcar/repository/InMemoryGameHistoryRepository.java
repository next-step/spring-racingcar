package racingcar.repository;

import lombok.AllArgsConstructor;
import racingcar.dto.GameHistory;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class InMemoryGameHistoryRepository implements BaseGameHistoryRepository {

  @AllArgsConstructor
  private class GameHistoryRow {
    private int id;
    private int trialCount;
    private String carNames;
    private String winners;
    private LocalDateTime createdAt;
  }

  private List<GameHistoryRow> gameHistoryRows = new LinkedList<>();
  private int sequence = 0;

  @Override
  public Integer save(String names, int count) {
    gameHistoryRows.add(new GameHistoryRow(++sequence, count, names, "", LocalDateTime.now()));
    return sequence;
  }

  @Override
  public void updateWinners(Integer gameId, String winners) {
    Optional<GameHistoryRow> foundGameHistory =
        gameHistoryRows.stream().filter(gameHistory -> gameHistory.id == gameId).findFirst();

    if (foundGameHistory.isEmpty()) {
      throw new IllegalArgumentException("해당 게임 번호에 대한 데이터가 존재하지 않습니다.");
    }

    GameHistoryRow gameHistory = foundGameHistory.get();
    gameHistory.winners = winners;
  }

  @Override
  public List<GameHistory> findAllWithHistory() {
    return null;
  }
}
