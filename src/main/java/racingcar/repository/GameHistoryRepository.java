package racingcar.repository;

import java.util.List;

import racingcar.domain.GameHistory;

public interface GameHistoryRepository {
	void saveAll(List<GameHistory> gameHistories);
}
