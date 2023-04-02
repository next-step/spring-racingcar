package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.RacingCarDao;
import racingcar.domain.RacingCar;
import racingcar.dto.PlaysReq;
import racingcar.dto.PlaysRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingCarService {
    private final RacingCarDao racingCarDao;

    public RacingCarService(RacingCarDao racingCarDao) {
        this.racingCarDao = racingCarDao;
    }


    public PlaysRes playRacing(PlaysReq playsReq) {

        // RacingCars 만들기
        String names = playsReq.getNames();
        List<RacingCar> racingCars = this.makeCars(names);

        // count만큼 Play
        int count = playsReq.getCount();
        for (int i = 0 ; i < count ; i++) {
            this.playRound(racingCars);
        }

        // 우승자 구하기
        List<String> winners = this.getWinners(racingCars);
        racingCarDao.insertPlayResult(String.join(",", winners), count);

        return new PlaysRes(winners, racingCars);

    }

    public List<RacingCar> makeCars(String names) {
        return Arrays.stream(names.split(","))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }

    public void playRound(List<RacingCar> racingCars) {
            Random random = new Random();
            for (RacingCar racingCar : racingCars) {
                int randomNumber = random.nextInt(10);
                racingCar.move(randomNumber);
                racingCarDao.insertPlayHistory(racingCar);
            }
    }

    public List<String> getWinners (List<RacingCar> racingCars) {
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

        return winners;
    }

}
