package racingcar.presentation.dto;

import java.util.Objects;

public class PlayHistory {

    private PlayResult playResult;
    private Long historyId;
    private String playerName;
    private Long position;

    public class PlayResult {
        private Long playResultId;
        private String winners;

        public PlayResult() {
        }

        public Long getPlayResultId() {
            return playResultId;
        }

        public String getWinners() {
            return winners;
        }

        public void setPlayResultId(Long playResultId) {
            this.playResultId = playResultId;
        }

        public void setWinners(String winners) {
            this.winners = winners;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PlayResult that = (PlayResult) o;

            if (!Objects.equals(playResultId, that.playResultId))
                return false;
            return Objects.equals(winners, that.winners);
        }

        @Override
        public int hashCode() {
            int result = playResultId != null ? playResultId.hashCode() : 0;
            result = 31 * result + (winners != null ? winners.hashCode() : 0);
            return result;
        }
    }

    public PlayHistory() {
    }

    public PlayResult getPlayResult() {
        return playResult;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Long getPosition() {
        return position;
    }

    public void setPlayResult(PlayResult playResult) {
        this.playResult = playResult;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
