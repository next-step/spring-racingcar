package racingcar.domain;

import java.util.List;

public class History {

    private String winners;
    private int trialCount;

    private int position;

    private String createdAt;
    private int groupId;

    private List<Car> racingCars;

    public History(String winners, int trialCount,int groupId) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.groupId = groupId;
    }


    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public List<Car> getRacingCars() { //  스크립트에서 사용
        return racingCars;
    }

    public void setRacingCars(List<Car> racingCars) {
        this.racingCars = racingCars;
    }


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "History{" +
                "winners='" + winners + '\'' +
                ", trialCount=" + trialCount +
                ", position=" + position +
                ", created_at='" + createdAt + '\'' +
                '}';
    }
}
