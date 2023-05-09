package racingcar.domain;

import java.time.LocalDateTime;

public class GameHistory {
	private final long id;
	private final long playResultId;
	private final String name;
	private final int position;

	private final LocalDateTime createdAt;

	public GameHistory(long id, long playResultId, String name, int position, LocalDateTime createdAt) {
		this.id = id;
		this.playResultId = playResultId;
		this.name = name;
		this.position = position;
		this.createdAt = createdAt;
	}

	public static GameHistory of(long gameResultId, Car car) {
		return GameHistory.builder()
			.playResultId(gameResultId)
			.name(car.getName())
			.position(car.getPosition())
			.build();
	}

	public static GameHistoryBuilder builder() {
		return new GameHistoryBuilder();
	}

	public long getId() {
		return id;
	}

	public long getPlayResultId() {
		return playResultId;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public static class GameHistoryBuilder {
		private long id;
		private long playResultId;
		private String name;
		private int position;

		private LocalDateTime createdAt;

		public GameHistoryBuilder id(long id) {
			this.id = id;
			return this;
		}

		public GameHistoryBuilder playResultId(long playResultId) {
			this.playResultId = playResultId;
			return this;
		}

		public GameHistoryBuilder name(String name) {
			this.name = name;
			return this;
		}

		public GameHistoryBuilder position(int position) {
			this.position = position;
			return this;
		}

		public GameHistoryBuilder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public GameHistory build() {
			return new GameHistory(id, playResultId, name, position, createdAt);
		}
	}
}

