package racingcar.domain.repository;

import racingcar.domain.PlayResult;

import java.util.List;

public interface PlayResultRepository {

    void insert(PlayResult playResult);

    List<PlayResult> findAll();
}
