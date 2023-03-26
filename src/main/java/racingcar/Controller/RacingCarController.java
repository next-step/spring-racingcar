package racingcar.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.Service.RacingService;
import racingcar.dto.PlayResultIn;
import racingcar.dto.PlayResultOut;

@RestController
public class RacingCarController {

    final
    RacingService racingService;

    public RacingCarController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResultOut> racingStart(@RequestBody PlayResultIn playResultIn) {
        return new ResponseEntity<>(racingService.racing(playResultIn.getNames(), playResultIn.getCount()),
            HttpStatus.OK);
    }

    @GetMapping("playsList")
    public ResponseEntity<List<PlayResultOut>> racingStart() {
        return new ResponseEntity<>(racingService.playList(),
            HttpStatus.OK);
    }
}
