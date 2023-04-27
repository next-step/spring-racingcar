package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingCarService;
import racingcar.dto.RacingRequestDto;
import racingcar.dto.RacingResultDto;
@RestController
public class RacingController {
  private final RacingCarService racingCarService;

  public RacingController(RacingCarService racingCarService) {
    this.racingCarService = racingCarService;
  }
  @PostMapping("/plays")
  public ResponseEntity<RacingResultDto> plays(@RequestBody RacingRequestDto racingRequestDto) {
    System.out.println(racingRequestDto);
    RacingResultDto resultDto = racingCarService.playRacing(racingRequestDto);

    return ResponseEntity.ok().body(resultDto);
  }
}
