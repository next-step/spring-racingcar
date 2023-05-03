package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import racingcar.domain.Cars;
import racingcar.dto.PlaysRequestDto;
import racingcar.dto.PlaysResponseDto;
import racingcar.service.PlayService;

@RestController
@RequiredArgsConstructor
public class RacingCarController {
	private final PlayService playService;

	@PostMapping("/plays")
	public PlaysResponseDto postPlays(@RequestBody PlaysRequestDto body) throws JsonProcessingException {
		Cars cars = playService.play(body.getNames(), body.getCount());
		return new PlaysResponseDto(cars);
	}
}
