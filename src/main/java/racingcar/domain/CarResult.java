package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class CarResult {

    private CarList carList;
    private List<String> winnerList = new ArrayList<>();

    public CarResult(CarList carList) {
        this.carList = carList;
    }

    public List<String> getWinnerList() {
        return winnerList;
    }

    public void carRacingResult() {
        int maxScore = maxScoreCalculate();

        carList.getCars().forEach(e -> {
            if (e.isAtPosition(maxScore)) {
                winnerList.add(e.getName());
            }
        });
    }

    public int maxScoreCalculate() {
        int maxValue = 0;

        for (int i = 0; i < carList.getCars().size(); i++) {
            if (maxValue < carList.getCars().get(i).getPosition()) {
                maxValue = carList.getCars().get(i).getPosition();
            }
        }
        return maxValue;
    }
}
