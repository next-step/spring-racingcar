package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.CarRacing;
import racingcar.domain.RacingCarRequest;
import racingcar.domain.CarResult;
import racingcar.view.CarNameRequest;
import racingcar.view.PlayNumberRequest;
import racingcar.domain.RacingCarResponse;

@RestController
public class CarController {

    @PostMapping(value = "/plays")
    public ResponseEntity<?> playRacing(@RequestBody RacingCarRequest racingCarParam) {
        CarNameRequest carNameRequest = CarNameRequest.carListRequest(racingCarParam.getNames());
        PlayNumberRequest playNumberRequest = PlayNumberRequest.playNumberRequest(racingCarParam.getCount());
        CarRacing carRacing = new CarRacing(carNameRequest.getCarNameList(), playNumberRequest.getNumber());
        carRacing.playAll();
        CarResult carResult = new CarResult(carRacing.getCarList());
        carResult.carRacingResult();
        return new ResponseEntity<>(new RacingCarResponse(racingCarParam.getCount(), carResult.getWinnerList(), carRacing.getCarList()).getRacingCarResponse(), HttpStatus.OK);
    }
}
