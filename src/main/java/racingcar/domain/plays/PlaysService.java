package racingcar.domain.plays;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.RacingCar;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaysService {

    private final PlaysRepository playsRepository;

    public PlaysDTO.Response create(PlaysDTO.Request request) {
        List<RacingCar> racingCars = createRacingCars(request.getNames());
        playRound(request.getCount(), racingCars);
        String winners = getWinners(racingCars);
        playsRepository.insert(winners);
        return createResponse(winners, racingCars);
    }

    private List<RacingCar> createRacingCars(String names) {
        return List.of(names.split(","))
                .stream()
                .map(String::trim)
                .map(RacingCar::new)
                .collect(Collectors.toList());
    }

    private void playRound(int count, List<RacingCar> racingCars) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            racingCars.forEach(car ->
                    car.move(random.nextInt(10))
            );
        }
    }

    private String getWinners(List<RacingCar> racingCars) {
        int maxPosition = racingCars.stream().mapToInt(RacingCar::getPosition).max().orElse(0);
        return racingCars.stream()
                .filter(racingCar -> racingCar.getPosition() == maxPosition)
                .map(RacingCar::getName)
                .collect(Collectors.joining(", "));
    }

    private PlaysDTO.Response createResponse(String winners, List<RacingCar> racingCars) {
        List<PlaysDTO.RacingCar> responseRacingCars = racingCars.stream()
                .map(car -> PlaysDTO.RacingCar.builder()
                        .name(car.getName())
                        .position(car.getPosition())
                        .build())
                .collect(Collectors.toList());

        return PlaysDTO.Response.builder()
                .winners(winners)
                .racingCars(responseRacingCars)
                .build();
    }

}
