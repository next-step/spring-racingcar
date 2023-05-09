package racingcar.domain.dao;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import racingcar.domain.GameHistory;
import racingcar.repository.GameHistoryRepository;

public class MemoryGameHistoryDao implements GameHistoryRepository {
	private static final String TABLE_NAME = "game_history";
	private static final String ID_COLUMN = "id";
	private static final String DATE_COLUMN = "created_at";

	private final Map<Long, GameHistory> store;
	private final AtomicLong id;

	public MemoryGameHistoryDao(DataSource dataSource) {
		this.store = new ConcurrentHashMap<>();
		this.id = new AtomicLong(1);
	}

	@Override
	public void saveAll(List<GameHistory> gameHistories) {
		gameHistories.stream()
			.forEach(gameHistory -> store.put(gameHistory.getId(), gameHistory));
	}

	@Override
	public List<GameHistory> findAll() {
		return store.values()
			.stream()
			.sorted(Comparator.comparing(GameHistory::getId))
			.collect(Collectors.toUnmodifiableList());
	}
}
