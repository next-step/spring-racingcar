package racingcar.repository.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import racingcar.domain.GameResult;
import racingcar.repository.GameResultRepository;

public class MemoryGameResultDao implements GameResultRepository {
	private final Map<Long, GameResult> store;
	private final AtomicLong id;

	public MemoryGameResultDao() {
		this.store = new ConcurrentHashMap<>();
		this.id = new AtomicLong(1);
	}

	@Override
	public long save(GameResult gameResult) {
		store.put(id.get(), gameResult);
		return id.getAndIncrement();
	}

	@Override
	public List<GameResult> findAll() {
		return store.values()
			.stream()
			.sorted(Comparator.comparing(GameResult::getId))
			.collect(Collectors.toUnmodifiableList());
	}
}
