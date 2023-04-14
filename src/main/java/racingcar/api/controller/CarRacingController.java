package racingcar.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.api.request.CarRacingRequest;
import racingcar.api.response.CarRacingResponse;
import racingcar.api.service.RaceControllService;

@RestController
public class CarRacingController {

    @Autowired
    private RaceControllService raceControllService;

    @PostMapping("/plays")
    public CarRacingResponse plays(@RequestBody CarRacingRequest request) {

        return raceControllService.race(request.getNames(), request.getCount());
    }

}
