package racingcar.model;

import java.util.Date;

public class PlayHistoryDao {

    private int id;

    private int turn;

    private String name;

    private int position;

    private Date createdAt;

    public PlayHistoryDao(int id, int turn, String name, int position, Date createdAt) {
        this.id = id;
        this.turn = turn;
        this.name = name;
        this.position = position;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getTurn() {
        return turn;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
