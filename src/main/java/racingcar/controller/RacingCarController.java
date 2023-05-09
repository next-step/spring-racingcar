package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import racingcar.dto.RacingPlaysRequest;
import racingcar.dto.RacingPlaysResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingService;

import java.util.List;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@RestController
@RequestMapping("/plays")
public class RacingCarController {
    private final RacingService racingService;

    public RacingCarController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping
    public RacingPlaysResponse plays(@RequestBody RacingPlaysRequest racingPlaysRequest){
        return racingService.playRace(racingPlaysRequest);
    }

    @GetMapping
    public List<RacingPlaysResponse> getPlayList(){
        return racingService.playRaceList();
    }
}
