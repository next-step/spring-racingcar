package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class Winners {
    private List<Car> winners;

    public Winners(List<Car> winners) {
        this.winners = winners;
    }

    public List<Car> getWinners() {
        return this.winners;
    }

    @Override
    public String toString() {
        return winners.stream()
                .map(e -> e.getName() + ", ")
                .collect(Collectors.joining());
    }

}
