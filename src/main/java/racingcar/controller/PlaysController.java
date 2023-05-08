package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import racingcar.dtos.request.PlaysRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dtos.response.PlayHistories;
import racingcar.dtos.response.PlaysResponseDto;
import racingcar.service.PlaysService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlaysController {
    private final PlaysService playsService;

    public PlaysController(PlaysService playsService) {
        this.playsService = playsService;
    }

    @PostMapping("/plays")
    public PlaysResponseDto getRacingCars(@RequestBody @Valid PlaysRequestDto playsRequestDto) {
        return playsService.play(playsRequestDto);
    }

    @GetMapping("/plays")
    public List<PlayHistories> getPlayHistories() {
        return playsService.getPlayHistories();
    }
}
