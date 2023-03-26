package racingcar.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import racingcar.Repository.PlayResultDAO;
import racingcar.Repository.RacingCarDAO;
import racingcar.model.PlayResult;
import racingcar.dto.PlayResultOut;
import racingcar.model.RacingCar;

@Service
public class RacingService {

    final
    PlayResultDAO playResultDAO;

    final
    RacingCarDAO racingCarDAO;

    private static final Logger logger = LogManager.getLogger(RacingService.class);

    public RacingService(PlayResultDAO playResultDAO,
        RacingCarDAO racingCarDAO) {
        this.playResultDAO = playResultDAO;
        this.racingCarDAO = racingCarDAO;
    }

    /**
     * 경기 기록 가져오기
     *
     * @return List<PlayResultOut>
     */
    public List<PlayResultOut> playList() {
        List<PlayResultOut> result = new ArrayList<>();
        List<PlayResult> list = playResultDAO.findAllPlayResult();
        for (PlayResult playResult : list) {
            List<RacingCar> racingCarByGroupId = racingCarDAO.findRacingCarByGroupId(
                playResult.getGroupId());
            PlayResultOut pro = new PlayResultOut(playResult.getWinners(), racingCarByGroupId);
            result.add(pro);
        }
        return result;
    }

    /**
     * 경기 시작 및 기록하기
     *
     * @param inputName
     * @param inputTryNumber
     * @return PlayResultOut
     */
    public PlayResultOut racing(String inputName, int inputTryNumber) {
        int groupId = racingCarDAO.getGroupId();
        String[] names = inputName.split(",");
        for (String name : names) {
            RacingCar racingCar = new RacingCar(groupId, name, 0);
            racingCarDAO.insertRacingCar(racingCar);
        }

        PlayResult playResult = new PlayResult(groupId, inputTryNumber, "");
        playResult = playResultDAO.insertPlayResult(playResult);

        List<RacingCar> racingCarList = racingCarDAO.findRacingCarByGroupId(groupId);
        for (int i = 0; i < inputTryNumber; i++) {
            playRound(racingCarList, groupId);
        }
        racingCarDAO.updateRacingCarList(racingCarList);

        List<RacingCar> winnerList = racingCarDAO.getWinner(groupId);
        List<String> winners = new ArrayList<>();
        for (RacingCar rc : winnerList) {
            winners.add(rc.getName());
        }
        playResult.setWinners(String.join(", ", winners));
        playResultDAO.updatePlayResult(playResult);

        PlayResultOut result = new PlayResultOut(playResult.getWinners(),
            racingCarDAO.findRacingCarByGroupId(groupId));
        return result;
    }

    /**
     * 실제 1경기 진행하기
     *
     * @param racingCarList
     * @param groupId
     */
    public void playRound(List<RacingCar> racingCarList, int groupId) {
        Random random = new Random();
        for (RacingCar racingCar : racingCarList) {
            int randomNumber = random.nextInt(10);
            logger.debug(racingCar.getName() + ":" + racingCar.getGroupId() + "/" + randomNumber);
            racingCar.move(randomNumber);
            //racingCarDAO.updatePosition(rc, randomNumber);
        }
    }
}
