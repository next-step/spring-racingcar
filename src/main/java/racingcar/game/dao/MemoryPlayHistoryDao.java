package racingcar.game.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import racingcar.game.domain.PlayerHistory;
import racingcar.game.domain.PlayerHistoryEntity;
import racingcar.game.domain.PlayerHistoryRepository;

public class MemoryPlayHistoryDao implements PlayerHistoryRepository {

    private final Map<Long, PlayerHistoryEntity> store;
    private final AtomicLong id;

    public MemoryPlayHistoryDao() {
        this.store = new ConcurrentHashMap<>();
        this.id = new AtomicLong(1);
    }

    @Override
    public void saveAll(List<PlayerHistory> playerHistories) {
        playerHistories.stream()
            .map(playerHistory -> playerHistory.toPlayerHistoryEntity(id.getAndIncrement()))
            .forEach(playerHistoryEntity -> store.put(playerHistoryEntity.getId(), playerHistoryEntity));
    }

    @Override
    public List<PlayerHistoryEntity> findAll() {
        return store.values()
            .stream()
            .sorted(Comparator.comparing(PlayerHistoryEntity::getId))
            .collect(Collectors.toUnmodifiableList());
    }
}
