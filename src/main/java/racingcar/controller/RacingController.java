package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingController {
    @Autowired
    private RacingCarService racingCarService;

    @PostMapping("/plays")
    public RacingCarResponseDto playGame(@RequestBody RacingCarRequestDto racingCarRequestDto) {
        return racingCarService.game(racingCarRequestDto.getNames(), racingCarRequestDto.getCount());
    }
}
