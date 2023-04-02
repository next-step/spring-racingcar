package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import racingcar.*;
import racingcar.dao.QueryingDAO;
import racingcar.dao.UpdatingDAO;
import racingcar.dto.RacingGameDTO;
import racingcar.repository.PlayResult;
import racingcar.repository.ResultRacing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class RacingController {
    @Autowired
    private UpdatingDAO updatingDao;
    @Autowired
    private QueryingDAO queryingDAO;

    @PostMapping("/plays")
    public ResultRacing plays(@RequestBody RacingGameDTO racingGameDTO) {
        CarRace carRace = new CarRace();
        Cars cars = new Cars(Stream.of(racingGameDTO.getNames().split(",")).collect(Collectors.toList()));

        for (int i = 0; i < racingGameDTO.getCount(); i++) {
            carRace.racing(cars);
        }

        Winners winners = carRace.getWinners(cars);

        int round = queryingDAO.getRoundNumber();

        // DB INSERT
        for (int i = 0; i < cars.getCars().size(); i++) {
            PlayResult playResult = new PlayResult(
                    round
                    , racingGameDTO.getCount()
                    , cars.getCars().get(i).getName().trim()
                    , cars.getCars().get(i).getPosition()
                    , winners.toString().trim());

            updatingDao.insert(playResult);
        }

        return new ResultRacing(winners.toString(), cars.getCars());
    }

    @GetMapping("/plays")
    public List<ResultRacing> history() {
        return queryingDAO.findHistory();
    }

}
