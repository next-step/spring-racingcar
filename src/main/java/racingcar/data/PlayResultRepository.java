package racingcar.data;

import racingcar.data.entity.PlayResult;

import java.util.List;

public interface PlayResultRepository {

    long insertGameResult(PlayResult playResult);

    List<PlayResult> getPlayResults();
}
