package racingcar.data.entity;

public class PlayResult {

    private long id;
    private String winners;

    public PlayResult(String winners) {
        this.winners = winners;
    }

    public PlayResult(long id, String winners) {
        this.id = id;
        this.winners = winners;
    }

    public long getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }
}
