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
import racingCar.model.RacingGameIn;
import racingCar.model.RacingGameOut;

@Service
public class RacingGameService {
    private PlayResultDAO playResultDAO;
    private PlayerRecordDAO playerRecordDAO;

    public RacingGameService(PlayResultDAO playResultDAO, PlayerRecordDAO playerRecordDAO) {
        this.playResultDAO = playResultDAO;
        this.playerRecordDAO = playerRecordDAO;
    }
    public RacingGameOut play(RacingGameIn inputData) {
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
        for (RacingCar racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }

        Timestamp playTime = new Timestamp(System.currentTimeMillis());
        int playId = playResultDAO.insert(count, toString(winners), playTime);

        for (RacingCar racingCar : racingCars) {
            playerRecordDAO.insert(playId, racingCar);
        }

        RacingGameOut gameResult = new RacingGameOut(winners.toString(), racingCars);

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
}