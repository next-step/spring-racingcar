package racingcar.dto;

import java.util.List;

public class GameHistoryResponseDto {
	private final List<RacingCarResponseDto> histories;

	public GameHistoryResponseDto(List<RacingCarResponseDto> histories) {
		this.histories = histories;
	}

	public List<RacingCarResponseDto> getHistories() {
		return histories;
	}
}
