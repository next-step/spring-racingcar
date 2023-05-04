package racingcar.web.controller;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RestController
public class PlayController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final PlayService playService;

    @PostMapping("/plays")
    public PlayResponseDto plays(@RequestBody PlayRequestDto playRequestDto) {
        List<PlayResult> playResults = playService.play(splitNames(playRequestDto.getNames()), playRequestDto.getCount());
        String winners = joinNames(playService.findWinners(playResults));

        playService.savePlayResults(playRequestDto.getCount(), winners, playResults);

        List<RacingCar> racingCars = playResults.stream()
                .map(playResult -> new RacingCar(playResult.getNameValue(), playResult.getPositionValue()))
                .collect(Collectors.toList());
        return new PlayResponseDto(winners, racingCars);
    }

    private String[] splitNames(String names) {
        return names.split(CAR_NAME_SEPARATOR);
    }

    private String joinNames(String[] names) {
        return String.join(CAR_NAME_SEPARATOR, names);
    }

}
