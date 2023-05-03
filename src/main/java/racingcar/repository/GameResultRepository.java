package racingcar.repository;

import racingcar.domain.GameResult;

public interface GameResultRepository {
	int save(GameResult gameResult);
}
