package racingcar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.application.StadiumService;
import racingcar.domain.CarCollection;
import racingcar.application.Stadium;
import racingcar.dto.PlaysDto;
import racingcar.dto.RacingCarResultDto;
import racingcar.view.CarRacingConsoleView;

import java.util.List;


@RestController
@RequestMapping("/")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    public static void main(String[] args) {

        CarRacingConsoleView view = new CarRacingConsoleView();

        String carNames = view.inputCarNames();
        int round = view.inputRound();

        // 경기를 만든다.
        Stadium stadium = new Stadium(new CarCollection(CarCollection.initCars(carNames)), round);

        view.beforeRacing();
        RacingCarResultDto resultDto = stadium.playRacingCar();

        // 끝난 경기에서 승자를 출력한다.
        view.showRacingResult(resultDto);
    }


    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacingCarResultDto> plays(@RequestBody PlaysDto playsDto) {

        return ResponseEntity.ok(stadiumService.playRacingResult(playsDto));
    }

    @GetMapping(value = "/allPlays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RacingCarResultDto>> allPlays(){

        return ResponseEntity.ok(stadiumService.allPlays());
    }

}
