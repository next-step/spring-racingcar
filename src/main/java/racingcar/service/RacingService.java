package racingcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.RacingCar;
import racingcar.controller.dto.RacingRequest;
import racingcar.controller.dto.RacingResponse;
import racingcar.controller.dto.RacingCarResponse;
import racingcar.jdbc.PlayCarResult;
import racingcar.jdbc.PlayResult;
import racingcar.jdbc.dao.insert.PlayCarResultInsertDao;
import racingcar.jdbc.dao.insert.PlayResultInsertDao;
import racingcar.jdbc.dao.query.PlayCarResultQueryDao;
import racingcar.jdbc.dao.query.PlayResultQueryDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacingService {

    private static Random random = new Random();
    private final PlayResultQueryDao playResultQueryDao;
    private final PlayCarResultQueryDao playCarResultQueryDao;
    private final PlayResultInsertDao playResultInsertDao;
    private final PlayCarResultInsertDao playCarResultInsertDao;


    public RacingResponse playGame(RacingRequest request) {
        List<RacingCar> cars = createCar(request.getNames());
        for (int i = 0; i < request.getCount(); i++) {
            playRound(cars);
        }
        List<String> winners = getWinner(cars);

        PlayResult insert = playResultInsertDao.insert(new PlayResult(request.getCount(), String.join(",", winners), LocalDateTime.now()));

        cars.forEach(car -> playCarResultInsertDao.insert(
                (new PlayCarResult(car.getName(), car.getPosition(), insert.getPlay_id(),LocalDateTime.now()))));


        return new RacingResponse(String.join(",", winners), cars.stream().map(car -> new RacingCarResponse(car.getName(), car.getPosition())).collect(Collectors.toList()));
    }

    public static List<RacingCar> createCar(String cars) {
        return Arrays.stream(cars.split(","))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }

    public static List<String> getWinner(List<RacingCar> cars) {
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

    public static void playRound(List<RacingCar> cars) {
        for (RacingCar racingCar : cars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }


    public List<RacingResponse> getPlayGameList() {
        List<RacingResponse> list = new ArrayList<>();
        int count = playResultQueryDao.count();
        for (int i = 1; i <= count; i++) {
            list.add(new RacingResponse(
                    playResultQueryDao.findWinnerById((long) i),
                    RacingCarResponse.of(playCarResultQueryDao.findByPlayResultId(i))
            ));
        }
        return list;
    }
}
