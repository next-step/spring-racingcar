package racingcar.dto;

import java.util.List;

<<<<<<< HEAD
import racingcar.domain.GameHistory;
import racingcar.dto.GameHistoryResponseDto;

public interface GameHistoryRepository {
	GameHistoryResponseDto findAllWithGameResults();

	void saveAll(List<GameHistory> gameHistories);
=======
public class GameHistoryResponseDto {
	private final List<RacingCarResponseDto> histories;

	public GameHistoryResponseDto(List<RacingCarResponseDto> histories) {
		this.histories = histories;
	}

	public List<RacingCarResponseDto> getHistories() {
		return histories;
	}
>>>>>>> 2946d161ed34a560e79e50dceaac96a9e4082f2c
}
