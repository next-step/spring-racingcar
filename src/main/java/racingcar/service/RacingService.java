package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.PlayInput;
import racingcar.domain.RacingCar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingService {

    private static List<RacingCar> racingCars;

    public static List<RacingCar> playgame(PlayInput playInput) {
        setCars(playInput.getNames());
        for (int i = 0; i < playInput.getCount(); i++) {
            playRound();
        }
        return racingCars;
    }

    private static void playRound() {
        Random random = new Random();

        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }

    public static void setCars(String names) {
        racingCars = Arrays.stream(names.split(","))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }

    public static List<String> getWinner() {
        int maxPosition = 0;

        List<String> winners = new ArrayList<>();
        for (RacingCar racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }

        return winners;
    }

}
