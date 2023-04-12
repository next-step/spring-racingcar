package racingcar.domain;

public class RacingGame {
    private final Cars participationCars;
    private final CarMoveEntropy carMoveEntropy;
    private int trialCount;

    public RacingGame(int trialCount, Cars participationCars, CarMoveEntropy carMoveEntropy) {
        this.participationCars = participationCars;
        this.carMoveEntropy = carMoveEntropy;
        this.trialCount = trialCount;
    }

    public RacingGame(Cars participationCars, CarMoveEntropy carMoveEntropy) {
        this(0, participationCars, carMoveEntropy);
    }

    public void startGame(int moveCount) {
        for (int i = 0; i < moveCount; i++) {
            this.trialCount++;
            this.participationCars.move(carMoveEntropy);
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
