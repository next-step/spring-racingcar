package racingcar.controller;

import racingcar.dto.RacingPlaysRequest;
import racingcar.dto.RacingPlaysResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@RestController
public class RacingCarController {

    @PostMapping("/plays")
    public RacingPlaysResponse plays(@RequestBody RacingPlaysRequest racingPlaysRequest){
        System.out.println("plays controller start");
        RacingPlaysResponse test = new RacingPlaysResponse();

        return test;
    }
}
