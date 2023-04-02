package racingcar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.application.StadiumService;

import racingcar.dto.PlaysDto;
import racingcar.dto.RacingCarResultDto;

import java.util.List;


@RestController
@RequestMapping("/")
public class StadiumController {

    private final StadiumService stadiumService;

    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacingCarResultDto> plays(@RequestBody PlaysDto playsDto) {

        return ResponseEntity.ok(stadiumService.playRacingResult(playsDto));
    }

    @GetMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RacingCarResultDto>> allPlays(){

        return ResponseEntity.ok(stadiumService.allPlays());
    }

}
