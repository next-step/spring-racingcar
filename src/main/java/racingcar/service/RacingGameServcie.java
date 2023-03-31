package racingcar.service;

import racingcar.dao.HistoryDao;
import racingcar.domain.History;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RacingGameServcie {

    static HistoryDao historyDao;

    public RacingGameServcie(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    private static List<RacingCar> racingCars;
    public static List<RacingCar> cargame(RacingInput racingInput) {

        setCars(racingInput.getNames());
        for (int i = 0; i < racingInput.getCount(); i++) {
            playRound();
        }
        return racingCars;
    }

    private static void playRound() {
        Random random = new Random();

        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }

    public static void setCars(String names) {
        racingCars = Arrays.stream(names.split(","))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }

    public static String getWinner() {

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

        return String.valueOf(winners);
    }

    public static List<History> History() {
        List<History> historyList  = HistoryDao.selectListPlayResult();
       return historyList;
    }


}
