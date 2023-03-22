package racingcar.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.Service.RacingService;
import racingcar.model.PlayResultIn;
import racingcar.model.PlayResultOut;

@RestController
public class RacingCarController {

    @Autowired
    RacingService svc;

    @PostMapping("/plays")
    public ResponseEntity<PlayResultOut> racingStart(@RequestBody PlayResultIn pri) {
        return new ResponseEntity<>(svc.racing(pri.getNames(), pri.getCount()),
            HttpStatus.OK);
    }

    @GetMapping("playsList")
    public ResponseEntity<List<PlayResultOut>> racingStart() {
        return new ResponseEntity<>(svc.playList(),
            HttpStatus.OK);
    }
}
