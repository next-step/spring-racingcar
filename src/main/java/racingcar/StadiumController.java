package racingcar;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.CarCollection;
import racingcar.domain.Stadium;
import racingcar.test.User;
import racingcar.view.CarRacingConsoleView;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/")
public class StadiumController {

    public static void main(String[] args) {

        CarRacingConsoleView view = new CarRacingConsoleView();

        String carNames = view.inputCarNames();
        int round = view.inputRound();


        // 경기를 만든다.
        Stadium stadium = new Stadium(new CarCollection(CarCollection.initCars(carNames)), round);

        view.beforeRacing();
        // 라운드에 따라 경기를 돌린다.
        while(!stadium.isRacingEnd()){
            view.showRacing(stadium.racingCars());
        }

        // 끝난 경기에서 승자를 출력한다.
        view.showWinners(stadium.getWinner());
    }


    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity plays(@RequestBody Map<String, Object> param) {
        Long id = 1L;
        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }


}
