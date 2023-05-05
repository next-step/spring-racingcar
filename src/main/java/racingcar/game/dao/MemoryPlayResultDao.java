package racingcar.game.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import racingcar.game.domain.PlayResult;
import racingcar.game.domain.PlayResultEntity;
import racingcar.game.domain.PlayResultRepository;

public class MemoryPlayResultDao implements PlayResultRepository {

    private final Map<Long, PlayResultEntity> store;
    private final AtomicLong id;

    public MemoryPlayResultDao() {
        this.store = new ConcurrentHashMap<>();
        this.id = new AtomicLong(1);
    }

    @Override
    public Long save(PlayResult playerResult) {
        store.put(id.get(), playerResult.toPlayResultEntity(id.get()));
        return id.getAndIncrement();
    }

    @Override
    public List<PlayResultEntity> findAll() {
        return store.values()
            .stream()
            .sorted(Comparator.comparing(PlayResultEntity::getId))
            .collect(Collectors.toUnmodifiableList());
    }
}
