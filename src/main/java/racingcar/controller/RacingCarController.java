package racingcar.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayInput;
import racingcar.dto.PlayResult;
import racingcar.service.RacingCarService;
import racingcar.dao.RacingCarDao;
import java.util.List;
@RestController
public class RacingCarController {
    @Autowired
    private RacingCarDao racingCarDao;

    @Autowired
    private RacingCarService racingCarService;

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity startGame(@RequestBody PlayInput playInput){
        PlayResult playResult = racingCarService.startgame(playInput);
        racingCarService.recordResult(playInput);
        return ResponseEntity.ok(playResult);
    }

    @GetMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayResult>> allPlayResult() {
        return ResponseEntity.ok(racingCarDao.getAllPlayResult());
    }
}
