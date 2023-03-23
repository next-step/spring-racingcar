package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.domain.Car;
import racingcar.domain.Device;
import racingcar.service.RacingCarGame;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
public class RacingcarController {

    @PostMapping("/plays")
    public ResponseEntity<RacingCarGame> requestParam(@RequestBody Device device) {
        System.out.println(device.getNames()+" " +device.getCount());
        String[] carNames = device.getNames().split(",");
        RacingCarGame carGame = new RacingCarGame(carNames, device.getCount());
        RacingCarGame result = carGame.start();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/plays")
    public ResponseEntity<String> requestParam1(String userName) {

        return ResponseEntity.ok().body("ok");
    }



}
