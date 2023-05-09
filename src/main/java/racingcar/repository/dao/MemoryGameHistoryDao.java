package racingcar.repository.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.Car;
import racingcar.domain.GameHistory;
import racingcar.dto.GameHistoryResponseDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.repository.GameHistoryRepository;

public class MemoryGameHistoryDao implements GameHistoryRepository {
	private static final List<GameHistory> gameHistories = new ArrayList<>();

	@Override
	public GameHistoryResponseDto findAllWithGameResults() {
		List<RacingCarResponseDto> racingCarResponseDtos = gameHistories.stream()
			.map(gameHistory -> {
				String name = gameHistory.getName();
				int position = gameHistory.getPosition();
				Car car = new Car(name, position);
				RacingCarResponseDto responseDto = new RacingCarResponseDto(null, Collections.singletonList(car));
				return responseDto;
			})
			.collect(Collectors.toList());

		return new GameHistoryResponseDto(racingCarResponseDtos);
	}

	@Override
	public void saveAll(List<GameHistory> gameHistories) {
		gameHistories.addAll(gameHistories);
	}
}
