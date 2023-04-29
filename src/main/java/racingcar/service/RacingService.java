package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.RacingCar;
import racingcar.controller.dto.RacingRequest;
import racingcar.controller.dto.RacingResponse;
import racingcar.controller.dto.RacingCarResponse;
import racingcar.jdbc.PlayCarResult;
import racingcar.jdbc.PlayResult;
import racingcar.jdbc.dao.PlayCarResultInsertDao;
import racingcar.jdbc.dao.PlayResultInsertDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingService {

    private PlayResultInsertDao playResultInsertDao;
    private PlayCarResultInsertDao playCarResultInsertDao;

    public RacingService(PlayResultInsertDao playResultInsertDao, PlayCarResultInsertDao playCarResultInsertDao) {
        this.playResultInsertDao = playResultInsertDao;
        this.playCarResultInsertDao = playCarResultInsertDao;
    }

    public RacingResponse playGame(RacingRequest request) {
        List<RacingCar> cars = createCar(request.getNames());
        for (int i = 0; i < request.getCount(); i++) {
            playRound(cars);
        }
        List<String> winners = getWinner(cars);

        // 데이터 저장
        cars.forEach(car -> playCarResultInsertDao.insertWithBeanPropertySqlParameterSource
                (new PlayCarResult(car.getName(), car.getPosition(), LocalDateTime.now())));

        System.out.println(String.join(",", winners));
        playResultInsertDao.insertWithBeanPropertySqlParameterSource(new PlayResult(request.getCount(), String.join(",", winners), LocalDateTime.now()));

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
