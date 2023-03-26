package racingcar.controller;

import org.springframework.web.bind.annotation.*;
import racingcar.*;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class RacingController {
    @PostMapping("/plays")
    @ResponseBody
    public ResultRacing plays(@RequestBody Map<String, String> map) {
        CarRace carRace = new CarRace();
        Cars cars = new Cars(Stream.of(map.get("names").split(",")).collect(Collectors.toList()));

        for (int i = 0; i < Integer.parseInt(map.get("count")); i++) {
            carRace.racing(cars);
        }

        Winners winners = carRace.getWinners(cars);

        return new ResultRacing(winners.toString(), cars.getCars());
    }
}
