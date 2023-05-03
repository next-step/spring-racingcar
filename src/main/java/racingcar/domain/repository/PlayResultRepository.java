package racingcar.domain.repository;

import racingcar.domain.entity.PlayResult;

import java.util.List;

public interface PlayResultRepository {
    void insert(PlayResult result);
    List<PlayResult> getAllPlayResults();
}
