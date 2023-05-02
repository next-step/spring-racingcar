package racingcar.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.PlayResult;
import racingcar.web.dto.PlayRequestDto;
import racingcar.web.dto.PlayResponseDto;
import racingcar.web.dto.PlayResponseDto.RacingCar;
import racingcar.web.service.PlayService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayController {

    private final PlayService playService;

    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    // TODO API 예외처리
    @PostMapping("/plays")
    public PlayResponseDto plays(@RequestBody PlayRequestDto playRequestDto) {
        List<PlayResult> playResults = playService.play(playRequestDto.getNames(), playRequestDto.getCount());

        String winners = playService.findWinners(playResults);
        List<RacingCar> racingCars = playResults.stream()
                .map(playResult -> new RacingCar(playResult.getNameValue(), playResult.getPositionValue()))
                .collect(Collectors.toList());

        return new PlayResponseDto(winners, racingCars);
    }

}
