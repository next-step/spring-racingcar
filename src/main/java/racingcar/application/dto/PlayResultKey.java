package racingcar.application.dto;

import java.util.Objects;

public class PlayResultKey {

    private final int playId;
    private final String winner;

    public PlayResultKey(int playId, String winner) {
        this.playId = playId;
        this.winner = winner;
    }

    public int getPlayId() {
        return playId;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayResultKey that = (PlayResultKey) o;
        return playId == that.playId && Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playId, winner);
    }
}
