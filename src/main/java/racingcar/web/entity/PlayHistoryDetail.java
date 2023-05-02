package racingcar.web.entity;

import java.util.Objects;

public class PlayHistoryDetail {

    private long id;
    private long playHistoryId;
    private String name;
    private int position;

    public PlayHistoryDetail(long id, long playHistoryId, String name, int position) {
        this.id = id;
        this.playHistoryId = playHistoryId;
        this.name = name;
        this.position = position;
    }

    public PlayHistoryDetail(long playHistoryId, String name, int position) {
        this.playHistoryId = playHistoryId;
        this.name = name;
        this.position = position;
    }

    public PlayHistoryDetail(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public long getPlayHistoryId() {
        return playHistoryId;
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
        PlayHistoryDetail that = (PlayHistoryDetail) o;
        return getId() == that.getId() && getPlayHistoryId() == that.getPlayHistoryId() && getPosition() == that.getPosition() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlayHistoryId(), getName(), getPosition());
    }
}
