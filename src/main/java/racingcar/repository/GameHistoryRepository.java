package racingcar.repository;

import java.util.List;

import racingcar.domain.GameHistory;

public interface GameHistoryRepository {
	GameHistoryResponseDto findAllWithGameResults();

	void saveAll(List<GameHistory> gameHistories);
	List<GameHistory> findAll();
}
