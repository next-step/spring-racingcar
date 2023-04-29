package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.RacingCar;
import racingcar.controller.dto.RacingRequest;
import racingcar.controller.dto.RacingResponse;
import racingcar.controller.dto.RacingCarResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingService {

    public RacingResponse playGame(RacingRequest request) {
        List<RacingCar> cars = createCar(request.getNames());
        for (int i = 0; i < request.getCount(); i++) {
            playRound(cars);
        }
        List<String> winners = getWinner(cars);

        return new RacingResponse(String.join(",", winners), cars.stream().map(car -> new RacingCarResponse(car.getName(), car.getPosition())).collect(Collectors.toList()));
    }

    private List<RacingCar> createCar(String cars) {
        return Arrays.stream(cars.split(","))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }

    private List<String> getWinner(List<RacingCar> cars) {
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();
        for (RacingCar racingCar : cars) {
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

    private void playRound(List<RacingCar> cars) {
        Random random = new Random();
        for (RacingCar racingCar : cars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }


}
