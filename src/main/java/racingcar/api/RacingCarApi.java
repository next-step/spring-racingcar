package racingcar.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.RacingCar;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameStartDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
public class RacingCarApi {

    @PostMapping("/plays")
    public GameResultDto playRacingCar(@RequestBody GameStartDto gameStartDto) {
        List<RacingCar> racingCars = Stream.of(gameStartDto.getNames().split(",")).map(RacingCar::new)
                .collect(Collectors.toList());
        int count = gameStartDto.getCount();

        IntStream.rangeClosed(1, count).forEach((i) -> playRound(racingCars));

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
        return new GameResultDto(String.join(", ", winners), racingCars);
    }

    private void playRound(List<RacingCar> racingCars) {
        Random random = new Random();
        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }
}
