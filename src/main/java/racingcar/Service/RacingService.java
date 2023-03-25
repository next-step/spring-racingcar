package racingcar.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.Repository.PersonDAO;
import racingcar.Repository.PlayResultDAO;
import racingcar.Repository.RacingCarDAO;
import racingcar.model.Person;
import racingcar.model.PlayResult;
import racingcar.model.PlayResultOut;
import racingcar.model.RacingCar;

@Service
public class RacingService {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    PlayResultDAO playResultDAO;

    @Autowired
    RacingCarDAO racingCarDAO;

    private static final Logger logger = LogManager.getLogger(RacingService.class);

    /**
     * 경기 기록 가져오기
     *
     * @return List<PlayResultOut>
     */
    public List<PlayResultOut> playList() {
        List<PlayResultOut> result = new ArrayList<>();
        List<PlayResult> list = playResultDAO.findAllPlayResult();
        for (PlayResult pr : list) {
            List<RacingCar> racingCarByGroupId = racingCarDAO.findRacingCarByGroupId(
                pr.getGroupId());
            PlayResultOut pro = new PlayResultOut(pr.getWinners(), racingCarByGroupId);
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
            Person person = new Person(name);
            int personId = personDAO.insertPerson(person);
            person.setId(personId);
            RacingCar racingCar = new RacingCar(groupId, personId, name, 0);
            racingCarDAO.insertRacingCar(racingCar);
        }

        PlayResult playResult = new PlayResult(groupId, inputTryNumber, "");
        playResult = playResultDAO.insertPlayResult(playResult);

        for (int i = 0; i < inputTryNumber; i++) {
            playRound(groupId);
        }

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
     * @param groupId
     */
    public void playRound(int groupId) {
        List<RacingCar> racingCarList = racingCarDAO.findRacingCarByGroupId(groupId);
        Random random = new Random();
        for (RacingCar rc : racingCarList) {
            int randomNumber = random.nextInt(10);
            logger.debug(rc.getName() + ":" + rc.getGroupId() + "/" + randomNumber);
            racingCarDAO.updatePosition(rc, randomNumber);
        }
    }
}
