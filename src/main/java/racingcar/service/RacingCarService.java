package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dto.RacingRequestDto;
import racingcar.dto.RacingResultDto;

public interface RacingCarService {
  RacingResultDto playRacing(RacingRequestDto racingRequestDto);
}
