package racingcar.domain;

import java.util.List;

public class PlayResult {

    private int id;
    private String winners;
    private int trialCount;

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public void updateId(int id) {
        this.id = id;
    }

    public static PlayResultBuilder builder() {
        return new PlayResultBuilder();
    }

    public static class PlayResultBuilder {
        private int id;
        private String winners;
        private int trialCount;

        public PlayResultBuilder id(int id) {
            this.id = id;
            return this;
        }

        public PlayResultBuilder winners(String winners) {
            this.winners = winners;
            return this;
        }

        public PlayResultBuilder winners(List<String> winners) {
            winners(String.join(",", winners));
            return this;
        }

        public PlayResultBuilder trialCount(int trialCount) {
            this.trialCount = trialCount;
            return this;
        }

        public PlayResult build() {
            PlayResult playResult = new PlayResult();
            playResult.id = this.id;
            playResult.winners = this.winners;
            playResult.trialCount = this.trialCount;
            return playResult;
        }
    }

}
