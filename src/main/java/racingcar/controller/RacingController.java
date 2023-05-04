package racingcar.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RaceResult;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingController {

  private final RacingCarService racingCarService;

  public RacingController(RacingCarService racingCarService) {
    this.racingCarService = racingCarService;
  }

  @PostMapping("/plays")
  public ResponseEntity<RacingResultDto> plays(@RequestBody RacingRequestDto racingRequestDto) {
    RacingResultDto resultDto = racingCarService.playRacing(racingRequestDto);

    return ResponseEntity.ok().body(resultDto);
  }

  @GetMapping("/plays")
  public ResponseEntity<List<RaceResult>> findAll(){
    return ResponseEntity.ok(racingCarService.findAll());
  }
}
