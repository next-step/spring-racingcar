package racingcar.repository;

import racingcar.domain.PlayResult;

public interface PlayResultCliCustomRepository<PlayResult> {
    PlayResult save(PlayResult playResult);
}