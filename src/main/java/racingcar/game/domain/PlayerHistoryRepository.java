package racingcar.game.domain;

import java.util.List;

public interface PlayerHistoryRepository {

    void saveAll(List<PlayerHistory> playerHistories);

    List<PlayerHistoryEntity> findAll();
}
