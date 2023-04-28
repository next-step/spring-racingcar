package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.RacingCar;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameStartDto;
import racingcar.repo.RacingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class RacingService {

    private final RacingRepository racingRepository;

    public RacingService(RacingRepository racingRepository) {
        this.racingRepository = racingRepository;
    }

    public GameResultDto game(GameStartDto gameStartDto) {
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

        GameResultDto gameResultDto = new GameResultDto(String.join(", ", winners), racingCars);
        racingRepository.insertGameResult(gameResultDto);
        return gameResultDto;
    }

    private void playRound(List<RacingCar> racingCars) {
        Random random = new Random();
        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }
}