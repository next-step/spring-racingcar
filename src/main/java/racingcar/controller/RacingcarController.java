package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.domain.Device;
import racingcar.domain.History;
import racingcar.domain.RacingCarGame;
import racingcar.service.RacingCarService;

import java.util.List;

@RestController
public class RacingcarController {
    private RacingCarService racingCarService;

    public RacingcarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingCarGame> plays(@RequestBody Device device) {
        RacingCarGame result = racingCarService.play(device.getNames(), device.getCount());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<History>> history() {
        List<History> result = racingCarService.getRacingGameHistory();

        return ResponseEntity.ok().body(result);
    }


}
