package racingCar;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingCar.model.RacingGameRequest;
import racingCar.model.RacingGameResponse;

@RestController
public class RacingGameController {

    RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }
    @PostMapping("/plays")
    public RacingGameResponse plays(@RequestBody RacingGameRequest inputData) {
        System.out.println(inputData.getNames() +"//" + inputData.getCount());

        return racingGameService.play(inputData);
    }




}
