package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingGameDTO;
import racingcar.dto.ResultRacingDTO;
import racingcar.service.RacingService;

import java.util.List;

@RestController
public class RacingController {

    @Autowired
    private RacingService racingService;

    public RacingController() {
    }

    @PostMapping("/plays")
    public ResultRacingDTO plays(@RequestBody RacingGameDTO racingGameDTO) {
        return racingService.playRacing(racingGameDTO);
    }

    @GetMapping("/plays")
    public List<ResultRacingDTO> history() {
        return racingService.resultHistory();
    }

}
