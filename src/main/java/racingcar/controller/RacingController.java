package racingcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.GameHistoryResponseDto;
import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.service.RacingCarService;

@RestController
@RequestMapping("/plays")
public class RacingController {
	@Autowired
	private RacingCarService racingCarService;

	@GetMapping
	public ResponseEntity<List<RacingCarResponseDto>> loadGameHistory() { // 변경된 반환 타입
		GameHistoryResponseDto historyResponseDto = racingCarService.loadGameHistory();
		return ResponseEntity.ok(historyResponseDto.getHistories()); // 변경된 응답
	}

	@PostMapping
	public ResponseEntity<RacingCarResponseDto> playGame(@RequestBody RacingCarRequestDto racingCarRequestDto) {
		RacingCarResponseDto carResponseDto = racingCarService.game(racingCarRequestDto);
		return ResponseEntity.ok(carResponseDto);
	}
}
