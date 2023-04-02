package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import racingcar.*;
import racingcar.dao.UpdatingDAO;
import racingcar.repository.PlayResult;
import racingcar.repository.ResultRacing;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class RacingController {
    @Autowired
    private UpdatingDAO updatingDao;

    @PostMapping("/plays")
    @ResponseBody
    public ResultRacing plays(@RequestBody Map<String, String> map) {
        CarRace carRace = new CarRace();
        Cars cars = new Cars(Stream.of(map.get("names").split(",")).collect(Collectors.toList()));

        for (int i = 0; i < Integer.parseInt(map.get("count")); i++) {
            carRace.racing(cars);
        }

        Winners winners = carRace.getWinners(cars);

        // DB INSERT
        for(int i = 0; i < cars.getCars().size(); i++) {
            PlayResult playResult = new PlayResult(Integer.parseInt(map.get("count"))
                    , cars.getCars().get(i).getName().trim()
                    , cars.getCars().get(i).getPosition()
                    , winners.toString().trim());

            updatingDao.insert(playResult);
        }

        return new ResultRacing(winners.toString(), cars.getCars());
    }
}
