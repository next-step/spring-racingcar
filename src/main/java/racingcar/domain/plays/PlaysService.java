package racingcar.domain.plays;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.RacingCar;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlaysService {

    private final PlaysRepository playsRepository;
    private final Random random = new Random();

    public PlaysDTO.Response create(PlaysDTO.Request request) {
        List<RacingCar> racingCars = createRacingCars(request.getNames());
        playRound(request.getCount(), racingCars);
        String winners = getWinners(racingCars);
        playsRepository.insertPlayResult(winners, request.getCount());
        insertPlayPositions(racingCars);
        return createResponse(winners, racingCars);
    }

    public List<PlaysDTO.Response> read() {
        return playsRepository.findAll();
    }

    private void insertPlayPositions(List<RacingCar> racingCars) {
        racingCars.forEach(racingCar -> playsRepository.insertPlayPosition(racingCar.getName(), racingCar.getPosition()));
    }

    private List<RacingCar> createRacingCars(String names) {
        return Stream.of(names.split(","))
                .map(String::trim)
                .map(RacingCar::new)
                .collect(Collectors.toList());
    }

    public void playRound(int count, List<RacingCar> racingCars) {
        for (int i = 0; i < count; i++) {
            racingCars.forEach(car ->
                    car.move(random.nextInt(10))
            );
        }
    }

    public String getWinners(List<RacingCar> racingCars) {
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
