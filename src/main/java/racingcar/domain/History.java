package racingcar.domain;

import java.util.List;

public class History {

    private String winners;
    private int trialCount;


    private int position;

    private String created_at;

    private List<Car> racingCars;

    public History(String winners, int trialCount, int position, String created_at) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.position = position;
        this.created_at = created_at;
    }


    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public void setTrialCount(int trialCount) {
        this.trialCount = trialCount;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(List<Car> racingCars) {
        this.racingCars = racingCars;
    }

    @Override
    public String toString() {
        return "History{" +
                "winners='" + winners + '\'' +
                ", trialCount=" + trialCount +
                ", position=" + position +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
