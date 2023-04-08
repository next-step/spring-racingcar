package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.dto.PlayInput;
import racingcar.dto.PlayResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
@Service
public class RacingCarService {
    @Autowired
    private RacingCarDao racingCarDao;

    static List<Car> racingCars = new ArrayList<>();

    public PlayResult startgame(PlayInput playInput) {
        setCars(playInput.getNames());
        for (int i = 0; i < playInput.getCount(); i++) {
            playRound();
        }
        PlayResult playResult =  new PlayResult(getWinner(), racingCars);
        return playResult;
    }

    public void recordResult(PlayInput playInput){
        int id = racingCarDao.insertPlayResult(playInput.getCount(), getWinner());
        racingCarDao.insertPlayCarHistory(id, racingCars);
    }

    public List<PlayResult> getAllResult(){
        return racingCarDao.getAllPlayResult();
    }

    private void playRound() {
        Random random = new Random();
        for (Car racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }
    public void setCars(String names) {
        racingCars = Arrays.stream(names.split(","))
                .map(it -> new Car(it.trim()))
                .collect(Collectors.toList());
    }
    public String getWinner() {
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();
        for (Car racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }
        return String.join(",", winners);
    }
}
