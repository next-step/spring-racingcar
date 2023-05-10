package racingcar.repository;

import java.util.List;

import racingcar.domain.GameHistory;
import racingcar.dto.GameHistoryResponseDto;

public interface GameHistoryRepository {
	GameHistoryResponseDto findAllWithGameResults();

	void saveAll(List<GameHistory> gameHistories);
}
