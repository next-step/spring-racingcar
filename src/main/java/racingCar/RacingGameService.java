package racingCar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingCar.dao.PlayResultDAO;
import racingCar.dao.PlayerRecordDAO;
import racingCar.domain.RacingCar;
import racingCar.model.RacingGameRequest;
import racingCar.model.RacingGameResponse;

@Service
public class RacingGameService {
    private final PlayResultDAO playResultDAO;
    private final PlayerRecordDAO playerRecordDAO;

    public RacingGameService(PlayResultDAO playResultDAO, PlayerRecordDAO playerRecordDAO) {
        this.playResultDAO = playResultDAO;
        this.playerRecordDAO = playerRecordDAO;
    }
    public RacingGameResponse play(RacingGameRequest inputData) {
        String names = inputData.getNames();
        int num = inputData.getCount();

        List<RacingCar> racingCars = Arrays.stream(names.split(","))
            .map(it -> new RacingCar(it.trim()))
            .collect(Collectors.toList());

        // 시도 횟수 입력
        int count = num;

        // 경주 시작
        for (int i = 0; i < count; i++) {
            playRound(racingCars);
        }

        // 우승자 조회
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();

        maxPosition = findMaxPosition(racingCars);
        winners = getWinner(racingCars, maxPosition);

        Timestamp playTime = new Timestamp(System.currentTimeMillis());
        int playId = playResultDAO.insert(count, toString(winners), playTime);

        for (RacingCar racingCar : racingCars) {
            playerRecordDAO.insert(playId, racingCar);
        }

        RacingGameResponse gameResult = new RacingGameResponse(winners.toString(), racingCars);

        return gameResult;
    }

    private String toString(List<String> inputs) {
        return String.join(", ", inputs);
    }

    private void playRound(List<RacingCar> racingCars) {
        Random random = new Random();
        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }
    private int findMaxPosition(List<RacingCar> cars) {
        int maxPosition = 0;
        for (RacingCar car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }
        return maxPosition;
    }

    private List<String> getWinner(List<RacingCar> cars, int maxPosition) {
        List<String> winners = new ArrayList<>();
        for (RacingCar car : cars) {
            setWinners(maxPosition, winners, car);
        }
        return winners;
    }

    private void setWinners(int maxPosition, List<String> winners, RacingCar car) {
        if (car.getPosition() == maxPosition) {
            winners.add(car.getName());
        }
    }
}