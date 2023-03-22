package racingcar.model;

public class RacingCar {

    private int id;
    private int groupId;
    private int personId;
    private String name;
    private int position;
    private String createdAt;

    public RacingCar(int id, int groupId, int personId, String name, int position) {
        this.id = id;
        this.groupId = groupId;
        this.personId = personId;
        this.name = name;
        this.position = position;
    }

    public RacingCar(int groupId, int personId, String name, int position) {
        this.groupId = groupId;
        this.personId = personId;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
