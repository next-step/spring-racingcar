package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingHistory;
import racingcar.jdbctemplate.InsertDao;
import racingcar.reponse.RacingResultResponse;
import racingcar.request.RacingStartRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class RacingCarController {

    @PostMapping("/plays")
    public RacingResultResponse plays(@RequestBody RacingStartRequest request) {
        int maxPosition = 0;


        String input = request.getNames();
        int count = request.getCount();

        List<RacingCar> racingCars = Arrays.stream(input.split(","))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());

        for(int i = 0; i < count; i ++) {
            Random random = new Random();
            for (RacingCar racingCar : racingCars) {
                int randomNumber = random.nextInt(10);
                racingCar.move(randomNumber);
            }
        }

        List<String> winners = new ArrayList<>();
        for (RacingCar racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }

        racingCars.forEach(it ->
                InsertDao.insertWithMap(it, count));

        RacingResultResponse response = new RacingResultResponse(String.join(", ", winners),racingCars);

        return response;
    }
}
