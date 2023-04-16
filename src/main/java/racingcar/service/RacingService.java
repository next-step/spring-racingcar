package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.CarRace;
import racingcar.Cars;
import racingcar.Winners;
import racingcar.dao.QueryingDAO;
import racingcar.dao.UpdatingDAO;
import racingcar.dto.RacingGameDTO;
import racingcar.dto.PlayResultDTO;
import racingcar.dto.ResultRacingDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class RacingService {

    @Autowired
    private UpdatingDAO updatingDao;
    @Autowired
    private QueryingDAO queryingDAO;

    public ResultRacingDTO playRacing(RacingGameDTO racingGameDTO) {
        CarRace carRace = new CarRace();
        Cars cars = new Cars(Stream.of(racingGameDTO.getNames().split(",")).collect(Collectors.toList()));

        for (int i = 0; i < racingGameDTO.getCount(); i++) {
            carRace.racing(cars);
        }

        Winners winners = carRace.getWinners(cars);

        int round = queryingDAO.getRoundNumber();

        // DB INSERT
        for (int i = 0; i < cars.getCars().size(); i++) {
            PlayResultDTO playResult = new PlayResultDTO(
                    round
                    , racingGameDTO.getCount()
                    , cars.getCars().get(i).getName().trim()
                    , cars.getCars().get(i).getPosition()
                    , winners.toString().trim());

            updatingDao.insert(playResult);
        }

        return new ResultRacingDTO(winners.toString(), cars.getCars());
    }

    public List<ResultRacingDTO> resultHistory() {
        return queryingDAO.findHistory();
    }
}
