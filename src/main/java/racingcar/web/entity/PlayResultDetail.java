package racingcar.web.entity;

import java.util.Objects;

public class PlayResultDetail {

    private long id;
    private long playResultId;
    private String name;
    private int position;

    public PlayResultDetail(long id, long playResultId, String name, int position) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public PlayResultDetail(long playResultId, String name, int position) {
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public PlayResultDetail(String name, int position) {
        this.name = name;
        this.position = position;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayResultDetail that = (PlayResultDetail) o;
        return getId() == that.getId() && getPlayResultId() == that.getPlayResultId() && getPosition() == that.getPosition() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlayResultId(), getName(), getPosition());
    }
}
