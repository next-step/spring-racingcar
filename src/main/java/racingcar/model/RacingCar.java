package racingcar.model;

public class RacingCar {

    private int id;
    private int groupId;
    private String name;
    private int position;

    public RacingCar(int id, int groupId, String name, int position) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.position = position;
    }

    public RacingCar(int groupId, String name, int position) {
        this.groupId = groupId;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move(int randomNumber) {
        if (randomNumber >= 4) {
            this.position += 1;
        }
    }
}
