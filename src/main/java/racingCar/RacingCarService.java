package racingCar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingCar.dao.PlayResultDAO;
import racingCar.dao.PlayerRecordDAO;
import racingCar.domain.RacingCar;
import racingCar.model.PlayHistoryResponse;
import racingCar.model.RacingGameRequest;
import racingCar.model.RacingGameResponse;
import racingCar.vo.PlayResult;
import racingCar.vo.PlayerRecord;

@Service
public class RacingCarService {
    private final PlayResultDAO playResultDAO;
    private final PlayerRecordDAO playerRecordDAO;

    public RacingCarService(PlayResultDAO playResultDAO, PlayerRecordDAO playerRecordDAO) {
        this.playResultDAO = playResultDAO;
        this.playerRecordDAO = playerRecordDAO;
    }
    public RacingGameResponse play(RacingGameRequest inputData) {
        String names = inputData.getNames();
        int count = inputData.getCount();

        List<RacingCar> racingCars = Arrays.stream(names.split(","))
            .map(it -> new RacingCar(it.trim()))
            .collect(Collectors.toList());

        RacingGameService racingRuleService = new RacingGameService();

        // 경주 시작
        for (int i = 0; i < count; i++) {
            racingRuleService.playRound(racingCars);
        }

        // 우승자 조회
        int maxPosition = racingRuleService.findMaxPosition(racingCars);
        List<String> winners = racingRuleService.getWinner(racingCars, maxPosition);

        Timestamp playTime = new Timestamp(System.currentTimeMillis());
        int playId = playResultDAO.insert(count, toString(winners), playTime);

        for (RacingCar racingCar : racingCars) {
            playerRecordDAO.insert(playId, racingCar);
        }

        RacingGameResponse gameResult = new RacingGameResponse(winners.toString(), racingCars);

        return gameResult;
    }

    public List<PlayHistoryResponse> getPlayHistory() {
        List<PlayHistoryResponse> playHistoryListResponse = new ArrayList<>();
        List<PlayResult> playResultList =playResultDAO.findAll();

        for(PlayResult playResult : playResultList) {
            List<PlayerRecord> playerRecordList = playerRecordDAO.findWithPlayId(playResult.getId());

            PlayHistoryResponse plplayHistoryResponse = new PlayHistoryResponse(playResult.getWinners(), playerRecordList);
            playHistoryListResponse.add(plplayHistoryResponse);
        }

        return playHistoryListResponse;
    }

    private String toString(List<String> inputs) {
        return String.join(", ", inputs);
    }

}