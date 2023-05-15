package racingcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.RacingCar;
import racingcar.controller.dto.RacingRequest;
import racingcar.controller.dto.RacingResponse;
import racingcar.controller.dto.RacingCarResponse;
import racingcar.jdbc.PlayResult;
import racingcar.jdbc.PlayRacingDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacingService {

    private static final Random RANDOM = new Random();
    private final PlayRacingDao playRacingDao;

    public RacingResponse playGame(RacingRequest request) {
        List<RacingCar> cars = createCar(request.getNames());
        for (int i = 0; i < request.getCount(); i++) {
            playRound(cars);
        }
        List<String> winners = getWinner(cars);

        playRacingDao.insert(PlayResult.of(request.getCount(), String.join(",", winners), cars, LocalDateTime.now()));

        return RacingResponse.of(String.join(",", winners), cars.stream().map(car -> new RacingCarResponse(car.getName(), car.getPosition())).collect(Collectors.toList()));
    }

    public List<RacingCar> createCar(String cars) {
        return Arrays.stream(cars.split(","))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }


    public List<String> getWinner(List<RacingCar> cars) {
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();
        for (RacingCar racingCar : cars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() == maxPosition) {
                winners.add(racingCar.getName());
            }
        }
        return winners;
    }

    public void playRound(List<RacingCar> cars) {
        for (RacingCar racingCar : cars) {
            int randomNumber = RANDOM.nextInt(10);
            racingCar.move(randomNumber);
        }
    }

    public List<RacingResponse> getPlayGame() {
        List<RacingResponse> list = new ArrayList<>();
        int count = playRacingDao.count();
        for (int i = 1; i <= count; i++) {
            list.add(new RacingResponse(
                    playRacingDao.findWinnerById((long) i),
                    RacingCarResponse.of(playRacingDao.getPlayCarResult((long) i))
            ));
        }
        return list;
    }

}
