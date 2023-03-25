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

    public int getGroupId() {
        return groupId;
    }


    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getCreatedAt() {
        return createdAt;
    }

}
