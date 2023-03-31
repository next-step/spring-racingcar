package racingcar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import racingcar.model.RacingResponse;
import racingcar.repository.CarConsoleRepository;
import racingcar.repository.CarsRepository;
import racingcar.repository.CarWebRepository;
import racingcar.repository.PlayResultCliRepository;
import racingcar.repository.PlayResultRepository;
import racingcar.repository.PlayResultWrapperRepository;
import racingcar.model.RacingRequest;
import racingcar.service.RacingCarService;
import racingcar.view.RacingResultView;

@RestController
@RequiredArgsConstructor
public class RacingCarController {
    private final CarWebRepository carsWebRepository;
    private final CarConsoleRepository carConsoleRepository;
    private final PlayResultRepository playResultRepository;
    private final PlayResultCliRepository playResultCliRepository;

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> racingGame(@RequestBody RacingRequest racingRequest) {
        CarsRepository carsRepository = new CarsRepository(carsWebRepository);
        PlayResultWrapperRepository playResultWrapperRepository = new PlayResultWrapperRepository(playResultRepository);
        RacingCarService racingCarService = new RacingCarService(carsRepository, playResultWrapperRepository);
        RacingResponse racingResponse = racingCarService.startRacing(racingRequest.getNames(),
                racingRequest.getCount());
        return ResponseEntity.ok().body(racingResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResponse>> racingHistory() {
        CarsRepository carsRepository = new CarsRepository(carsWebRepository);
        PlayResultWrapperRepository playResultWrapperRepository = new PlayResultWrapperRepository(playResultRepository);
        RacingCarService racingCarService = new RacingCarService(carsRepository, playResultWrapperRepository);
        List<RacingResponse> racingHistory = racingCarService.getRacingHistory();
        return ResponseEntity.ok().body(racingHistory);
    }

    public void consoleRacingGame(String carNames, int targetDistance) {
        CarsRepository carsRepository = new CarsRepository(carConsoleRepository);
        PlayResultWrapperRepository playResultWrapperRepository = new PlayResultWrapperRepository(
                playResultCliRepository);
        RacingCarService racingCarService = new RacingCarService(carsRepository, playResultWrapperRepository);
        RacingResponse racingResponse = racingCarService.startRacing(carNames, targetDistance);
        RacingResultView.printRacingResponse(racingResponse);
    }

}
