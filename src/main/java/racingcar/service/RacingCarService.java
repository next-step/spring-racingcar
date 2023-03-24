package racingcar.service;


import org.springframework.stereotype.Service;
import racingcar.dao.HistoryDAO;
import racingcar.domain.Car;
import racingcar.domain.History;
import racingcar.domain.RacingCarGame;

import java.util.List;

@Service
public class RacingCarService {
    private HistoryDAO historyDAO;

    public RacingCarService(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    public RacingCarGame play(String names, int count) {
        String[] carNames = names.split(",");
        RacingCarGame carGame = new RacingCarGame(carNames, count);
        RacingCarGame result = carGame.start();
        for (Car car : result.getRacingCars()) {
            System.out.println(car.getName());
            historyDAO.insert(car, result.getTotalTry(), result.getWinners());
        }
        return result;
    }

    public List<History> getRacingGameHistory() {

        List<History> historyList = historyDAO.selectListPlayResult();

        for (History history : historyList) {

            List<Car> cars = historyDAO.selectListPlay(history);
            history.setRacingCars(cars);
        }

        return historyList;
    }


}
