package racingcar.domain;

import java.time.LocalDateTime;

public class GameResult {
	private final String winners;
	private final int trialCount;

	private LocalDateTime createdAt;

	public GameResult(int id, String winners, int trialCount, LocalDateTime createdAt) {
		this.id = id;
		this.winners = winners;
		this.trialCount = trialCount;
		this.createdAt = createdAt;
	}

	public static GameResultBuilder builder() {
		return new GameResultBuilder();
	}

	public int getId() {
		return id;
	}

	public String getWinners() {
		return winners;
	}

	public int getTrialCount() {
		return trialCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public static class GameResultBuilder {
		private int id;
		private String winners;
		private int trialCount;

		private LocalDateTime createdAt;

		public GameResultBuilder id(int id) {
			this.id = id;
			return this;
		}

		public GameResultBuilder winners(String winners) {
			this.winners = winners;
			return this;
		}

		public GameResultBuilder trialCount(int trialCount) {
			this.trialCount = trialCount;
			return this;
		}

		public GameResultBuilder createAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public GameResult build() {
			return new GameResult(id, winners, trialCount, createdAt);
		}
	}
}
