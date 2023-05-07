package racingcar.domain;

public class PlayHistory {
    private Integer playResultId;
    private String name;
    private int position;

    public Integer getPlayResultId() {
        return playResultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPlayResultId(Integer playResultId) {
        this.playResultId = playResultId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
