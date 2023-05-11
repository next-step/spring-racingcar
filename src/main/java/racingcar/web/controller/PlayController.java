package racingcar.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.dto.PlayResultDto;
import racingcar.web.dto.PlayHistoryDto;
import racingcar.web.dto.PlayRequestDto;
import racingcar.web.dto.PlayResponseDto;
import racingcar.service.PlayService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PlayController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final PlayService playService;

    @GetMapping("/plays")
    public List<PlayResponseDto> history() {
        List<PlayHistoryDto> historyDtos = playService.history();

        return historyDtos.stream()
                .map(PlayHistoryDto::toPlayResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/plays")
    public PlayResponseDto plays(@RequestBody PlayRequestDto playRequestDto) {
        // TODO Play 로직 Service에서 수행하도록 변경하기
        List<PlayResultDto> playResultDtos = playService.play(splitNames(playRequestDto.getNames()), playRequestDto.getCount());
        String winners = joinNames(playService.findWinners(playResultDtos));

        playService.savePlayResults(playRequestDto.getCount(), winners, playResultDtos);

        List<PlayResponseDto.RacingCar> racingCars = playResultDtos.stream()
                .map(PlayResponseDto.RacingCar::new)
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
