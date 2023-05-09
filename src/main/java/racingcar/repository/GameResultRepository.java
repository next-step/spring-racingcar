package racingcar.repository;

import java.util.List;

import racingcar.domain.GameResult;

public interface GameResultRepository {
	long save(GameResult gameResult);

	List<GameResult> findAll();
}
