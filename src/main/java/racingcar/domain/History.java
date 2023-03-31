package racingcar.domain;

import java.util.List;

public class History {

    private String winners;
    private int trialCount;

    private String created_at;

    private List<RacingCar> racingCars;

    public History(String winners, int trialCount, String created_at) {
        this.winners = winners;
        this.trialCount = trialCount;
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


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    @Override
    public String toString() {
        return "History{" +
                "winners='" + winners + '\'' +
                ", trialCount=" + trialCount +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
