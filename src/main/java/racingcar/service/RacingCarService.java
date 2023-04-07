package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.dto.PlayInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    public List<Car> racingCars = new ArrayList<>();

    public List<Car> startgame(PlayInput playInput) {
        setCars(playInput.getNames());
        for (int i = 0; i < playInput.getCount(); i++) {
            playRound();
        }
        return racingCars;
    }

    private void playRound() {
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

    public String getWinner() {
        int maxPosition = 0;

        List<String> winners = new ArrayList<>();
        for (Car racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }
        return String.join(",", winners);
    }

}
