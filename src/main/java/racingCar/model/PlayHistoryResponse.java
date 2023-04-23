package racingCar.model;

import java.util.List;
import racingCar.domain.RacingCar;
import racingCar.vo.PlayerRecord;

public class PlayHistoryResponse {
    String winners;

    List<PlayerRecord> racingCars;

    public PlayHistoryResponse(String winners, List<PlayerRecord> racingCars ) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public List<PlayerRecord> getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(List<PlayerRecord> racingCars) {
        this.racingCars = racingCars;
    }
}
