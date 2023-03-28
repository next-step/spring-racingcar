package racingcar;

import org.springframework.stereotype.Service;
import racingcar.Repository.RacingCarDAO;
import racingcar.model.RacingResultIn;
import racingcar.model.RacingResultOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RacingService {
    RacingCarDAO racingCarDAO;

    public RacingService(RacingCarDAO racingCarDAO) {
        this.racingCarDAO = racingCarDAO;
    }

    public RacingResultOut racingStart(RacingResultIn in) {

        RacingResultOut racingResultOut = new RacingResultOut();

        List<RacingCar> racingCars = Arrays.stream(in.getNames().split(","))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());

        int count = in.getCount();
        // 경주 시작
        for (int i = 0; i < count; i++) {
            playRound(racingCars);
        }
        // 우승자 조회
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

        int nextID = racingCarDAO.racingInfoInsert(count, String.valueOf(winners));
        racingCarDAO.racingPlayerInsert(nextID, racingCars);

        racingResultOut.setRacingCars(racingCars);
        racingResultOut.setWinners(String.valueOf(winners));

        return racingResultOut;
    }

    private static void playRound(List<RacingCar> racingCars) {
        Random random = new Random();
        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }
}
