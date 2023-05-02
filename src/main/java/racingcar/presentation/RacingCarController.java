package racingcar.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.application.RacingCarService;
import racingcar.dto.RacingCarPlayRequest;
import racingcar.dto.RacingCarPlayResponse;

@RestController
@RequiredArgsConstructor
public class RacingCarController {

  private final RacingCarService racingCarService;

  @PostMapping("/plays")
  public ResponseEntity<RacingCarPlayResponse> play(@RequestBody RacingCarPlayRequest request) {
    RacingCarPlayResponse response =
        this.racingCarService.play(request.getNames(), request.getCount());

    return ResponseEntity.ok(response);
  }
}
