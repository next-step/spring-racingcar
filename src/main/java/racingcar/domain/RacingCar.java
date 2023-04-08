package racingcar.domain;

import org.springframework.stereotype.Service;
import racingcar.dto.RacingInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingCar {

    public List<Car> racingCars = new ArrayList<>();

    public List<Car> racingGame(RacingInput racingInput) {
        setCars(racingInput.getNames());
        for (int i = 0; i < racingInput.getCount(); i++) {
            playRandomRound();
        }
        return racingCars;
    }

    public void playRandomRound() {
        Random random = new Random();

        for (Car racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }

    public void setCars(String names) {
        racingCars = Arrays.stream(names.split(","))
                .map(it -> new Car(it.trim()))
                .collect(Collectors.toList());
    }

    public List<String> getWinner() {
        int maxPosition = 0;

        List<String> winners = new ArrayList<>();
        maxPosition = getMaxPosition();
        for (Car racingCar : racingCars) {
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }
        return winners;
    }

    // 최대 위치를 찾는 메서드
    public int getMaxPosition() {
        return racingCars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }


}