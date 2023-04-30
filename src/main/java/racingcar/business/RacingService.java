package racingcar.business;

import org.springframework.stereotype.Service;
import racingcar.RacingCar;
import racingcar.presentation.dto.GameResultDto;
import racingcar.presentation.dto.GameStartDto;
import racingcar.data.RacingRepository;

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
        List<RacingCar> racingCars = getRacingCars(gameStartDto);
        int count = gameStartDto.getCount();
        startCarRacingGame(racingCars, count);

        GameResultDto gameResultDto = calculateGameResult(racingCars);
        racingRepository.insertGameResult(gameResultDto);
        return gameResultDto;
    }

    public int startCarRacingGame(List<RacingCar> racingCars, int count) {
        return IntStream.rangeClosed(1, count).reduce(0, (acc, n) -> {
            this.playRound(racingCars);
            return acc + 1;
        });
    }

    public List<RacingCar> getRacingCars(GameStartDto gameStartDto) {
        return Stream.of(gameStartDto.getNames().split(",")).map(RacingCar::new)
                .collect(Collectors.toList());
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