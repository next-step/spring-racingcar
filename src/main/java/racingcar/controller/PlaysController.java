package racingcar.controller;

import racingcar.dtos.request.PlaysRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dtos.response.PlaysResponseDto;
import racingcar.service.PlaysService;

@RestController
public class PlaysController {
    private final PlaysService playsService;

    public PlaysController(PlaysService playsService) {
        this.playsService = playsService;
    }

    @PostMapping("/plays")
    public PlaysResponseDto getRacingCars(@RequestBody PlaysRequestDto playsRequestDto) {
        return playsService.play(playsRequestDto);
    }
}
