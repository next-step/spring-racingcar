package racingCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import racingCar.domain.RacingCar;

public class RacingGameService {

    public void playRound(List<RacingCar> racingCars) {
        Random random = new Random();
        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }


    public int findMaxPosition(List<RacingCar> cars) {
        int maxPosition = 0;
        for (RacingCar car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }
        return maxPosition;
    }

    public List<String> getWinner(List<RacingCar> cars, int maxPosition) {
        List<String> winners = new ArrayList<>();
        for (RacingCar car : cars) {
            setWinners(maxPosition, winners, car);
        }
        return winners;
    }

    public void setWinners(int maxPosition, List<String> winners, RacingCar car) {
        if (car.getPosition() == maxPosition) {
            winners.add(car.getName());
        }
    }
}
