package racingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.RacingHistory;
import racingcar.dto.RacingInput;

import racingcar.dao.RacingCarDao;
import racingcar.dto.RacingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingCar {

    @Autowired
    private static RacingCarDao racingCarDao;

    public static List<Car> racingCars = new ArrayList<>();

    public static List<Car> racingGame(RacingInput racingInput) {
        setCars(racingInput.getNames());
        for (int i = 0; i < racingInput.getCount(); i++) {
            playRandomRound();
        }
        return racingCars;
    }

    public static void playRandomRound() {
        Random random = new Random();

        for (Car racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }

    public static void setCars(String names) {
        racingCars = Arrays.stream(names.split(","))
                .map(it -> new Car(it.trim()))
                .collect(Collectors.toList());
    }

    public List<String> getWinner() {
        int maxPosition = 0;

        List<String> winners = new ArrayList<>();
        maxPosition = getMaxPosition();
        for (Car racingCar : racingCars) {
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }
        return winners;
    }

    // 최대 위치를 찾는 메서드
    public int getMaxPosition() {
        return racingCars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public static List<RacingHistory> getAllResult(){

        return racingCarDao.getGameHistory();
    }



}