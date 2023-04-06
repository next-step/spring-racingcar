package racingcar.domain;

import org.springframework.stereotype.Service;
import racingcar.dto.PlayInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingCar {

    private static List<Car> racingCars = new ArrayList<>();

    public static List<Car> startgame(PlayInput playInput) {
        setCars(playInput.getNames());
        for (int i = 0; i < playInput.getCount(); i++) {
            playRound();
        }
        return racingCars;
    }

    private static void playRound() {
        Random random = new Random();

        for (Car racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }

    public static void setCars(String names) {
        racingCars = Arrays.stream(names.split(","))
                .map(it -> new Car(it.trim()))
                .collect(Collectors.toList());
    }

    public static String getWinner() {
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

        String str = String.join(",", winners);
        System.out.println(str);

        return str;
    }

}