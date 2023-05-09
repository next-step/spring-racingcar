package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RaceResult;
import racingcar.dto.RacingRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.service.RacingCarService;

@RestController
@RequestMapping("/plays")
public class RacingController {

  private final RacingCarService racingCarService;

  public RacingController(RacingCarService racingCarService) {
    this.racingCarService = racingCarService;
  }

  @PostMapping
  public ResponseEntity<RacingResultDto> plays(@RequestBody RacingRequestDto racingRequestDto) {
    RacingResultDto resultDto = racingCarService.playRacing(racingRequestDto);

    return ResponseEntity.ok().body(resultDto);
  }

  @GetMapping
  public ResponseEntity<List<RaceResult>> findAll() {
    return ResponseEntity.ok(racingCarService.findAll());
  }
}
