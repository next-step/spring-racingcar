package racingcar.service;


import org.springframework.stereotype.Service;
import racingcar.dao.GroupDAO;
import racingcar.dao.HistoryDAO;
import racingcar.domain.Car;
import racingcar.domain.History;
import racingcar.domain.RacingCarGame;

import java.util.List;

@Service
public class RacingCarService {
    private HistoryDAO historyDAO;

    private GroupDAO groupDAO;

    public RacingCarService(HistoryDAO historyDAO, GroupDAO groupDAO) {
        this.historyDAO = historyDAO;
        this.groupDAO = groupDAO;
    }

    public RacingCarGame play(String names, int count) {
        String[] carNames = names.split(",");
        RacingCarGame carGame = new RacingCarGame(carNames, count);
        RacingCarGame result = carGame.start();
        int groupId = groupDAO.insertGroup(result.getTotalTry(), result.getWinners());

        for (Car car : result.getRacingCars()) {
            historyDAO.insertPlayResult(car, groupId );
        }
        return result;
    }

    public List<History> getRacingGameHistory() {

        List<History> historyList = groupDAO.selectListPlayResult();


        for (History history : historyList) {
            List<Car> cars = historyDAO.selectListPlay(history);
            history.setRacingCars(cars);
        }

        return historyList;
    }


}
