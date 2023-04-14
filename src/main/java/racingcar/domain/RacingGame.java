package racingcar.domain;

public class RacingGame {
    private final Cars participationCars;
    private final CarMoveDeterminer carMoveDeterminer;
    private int trialCount;

    public RacingGame(int trialCount, Cars participationCars, CarMoveDeterminer carMoveDeterminer) {
        this.participationCars = participationCars;
        this.carMoveDeterminer = carMoveDeterminer;
        this.trialCount = trialCount;
    }

    public RacingGame(Cars participationCars, CarMoveDeterminer carMoveDeterminer) {
        this(0, participationCars, carMoveDeterminer);
    }

    public void startGame(int moveCount) {
        for (int i = 0; i < moveCount; i++) {
            this.trialCount++;
            this.participationCars.move(carMoveDeterminer);
        }
    }

    public Cars getParticipationCars() {
        return this.participationCars;
    }

    public Cars getRacingWinners() {
        return this.participationCars.maxCars();
    }

    public boolean isWinner(Car participationCar) {
        return getRacingWinners().isMatch(participationCar);
    }

    public int getTrialCount() {
        return this.trialCount;
    }
}
