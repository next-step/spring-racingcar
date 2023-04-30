package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.RacingCar;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameStartDto;
import racingcar.repo.RacingRepository;

import java.util.List;
import java.util.Optional;
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

        GameResultDto gameResultDto = calculateGameResult(racingCars);
        racingRepository.insertGameResult(gameResultDto);
        return gameResultDto;
    }

    private GameResultDto calculateGameResult(List<RacingCar> racingCars) {
        Optional<Integer> maxPosition = racingCars.stream().map(RacingCar::getPosition).max(Integer::compareTo);
        String winners = racingCars.stream().filter(racingCar -> racingCar.getPosition() == maxPosition.orElseThrow())
                .map(RacingCar::getName).collect(Collectors.joining(","));
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