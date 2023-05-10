package racingcar.application.dto;

public class PlayResult {
    private final String winners;
    private final int playId;
    private final String name;
    private final int position;

    public PlayResult(String winners, int playId, String name, int position) {
        this.winners = winners;
        this.playId = playId;
        this.name = name;
        this.position = position;
    }

    public String getWinners() {
        return winners;
    }

    public int getPlayId() {
        return playId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
