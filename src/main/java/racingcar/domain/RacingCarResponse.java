package racingcar.domain;

import racingcar.dao.RacingInsertDao;
import racingcar.domain.Car;
import racingcar.domain.CarList;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacingCarResponse {

    private Map<String, Object> result = new HashMap<>();
    private List<Car> carList;
    private List<String> winnerList;

    public RacingCarResponse(int trialCount, List<String> winnerList, CarList carList) {
        this.winnerList = winnerList;
        this.carList = carList.getCars();
        racingInsert(trialCount, winnerList, carList.getCars());
        responseDataSave();
    }

    private void responseDataSave() {
        result.put("winners", winnerList);
        result.put("racingCars", carList);
    }

    private void racingInsert(int trialCount, List<String> winnerList, List<Car> carList) {
        carList.forEach(e -> RacingInsertDao.insert(trialCount, winnerList, e));
    }

    public Map<String, Object> getRacingCarResponse() {
        return result;
    }
}
