package racingcar.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.behavior.RandomMovingStrategy;
import racingcar.domain.GameHistory;
import racingcar.domain.GameResult;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingCarResponseDto;
import racingcar.repository.GameHistoryRepository;
import racingcar.repository.GameResultRepository;

@Service
public class RacingCarService {
	private final GameHistoryRepository gameHistoryRepository;
	private final GameResultRepository gameResultRepository;

	public RacingCarService(GameHistoryRepository gameHistoryRepository, GameResultRepository gameResultRepository) {
		this.gameHistoryRepository = gameHistoryRepository;
		this.gameResultRepository = gameResultRepository;
	}

	public RacingCarResponseDto game(String nameOfCars, int finalRound) {
		List<Car> initCars = createCars(nameOfCars);

		RacingGame racingGame = RacingGame.of(initCars, new RandomMovingStrategy());
		racingGame.play(finalRound);

		int playResultId = saveGameResult(racingGame, finalRound);
		saveGameHistories(playResultId, initCars);

		return new RacingCarResponseDto(racingGame.getWinners(), initCars);
	}

	private long saveGameResult(RacingGame racingGame, int round) {
		GameResult gameResult = GameResult.builder()
			.winners(racingGame.getNameOfWinnerCar())
			.trialCount(round)
			.build();
		return gameResultRepository.save(gameResult);
	}

	private void saveGameHistories(long playResultId, RacingGame cars) {
		List<GameHistory> gameHistories = cars.getCars()
			.stream()
			.map(it -> GameHistory.of(playResultId, it))
			.collect(Collectors.toList());
		gameHistoryRepository.saveAll(gameHistories);
	}

	private List<Car> createCars(String nameOfCars) {
		String[] nameOfCarsArr = nameOfCars.replaceAll("\"", "").split(",");
		return Arrays.stream(nameOfCarsArr)
			.map(Car::new)
			.collect(Collectors.toList());
	}

}
