package racingcar.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import racingcar.domain.GameHistory;
import racingcar.domain.GameResult;

public class GameHistoryResponseDto {
	private final List<RacingCarResponseDto> histories;

	public GameHistoryResponseDto(List<RacingCarResponseDto> histories) {
		this.histories = histories;
	}

	public static GameHistoryResponseDto of(List<GameResult> gameResults, List<GameHistory> gameHistories) {
		Map<Long, List<GameHistory>> historyByGameId = gameHistories.stream()
			.collect(Collectors.groupingBy(GameHistory::getPlayResultId));

		List<RacingCarResponseDto> carResponseDtos = gameResults.stream()
			.map(it -> RacingCarResponseDto.of(it, historyByGameId.get(it.getId())))
			.collect(Collectors.toList());
		return new GameHistoryResponseDto(carResponseDtos);
	}

	public List<RacingCarResponseDto> getHistories() {
		return histories;
	}
}
