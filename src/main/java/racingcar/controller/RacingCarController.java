package racingcar.controller;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.PlaysRequestDto;
import racingcar.dto.PlaysResponseDto;

@RestController
public class RacingCarController {
	@PostMapping("/plays")
	public PlaysResponseDto postPlays(@RequestBody PlaysRequestDto body) {
		System.out.println("names : "+body.getNames());
		Cars cars = new Cars(body.getNames());
		cars.play(body.getCount());
		return new PlaysResponseDto(cars);
	}
}
