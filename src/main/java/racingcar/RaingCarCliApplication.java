package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import racingcar.controller.RacingCarController;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.model.RacingResponse;
import racingcar.repository.CarConsoleRepository;
import racingcar.repository.CarsRepository;
import racingcar.repository.PlayResultCliRepository;
import racingcar.repository.PlayResultRepository;
import racingcar.repository.PlayResultWrapperRepository;
import racingcar.service.RacingCarService;
import racingcar.view.RacingInputCarView;
import racingcar.view.RacingResultView;

public class RaingCarCliApplication {
    public static void main(String[] args) {
        CarsRepository carsRepository = new CarsRepository(null) {
            @Override
            public void save(Cars cars) {
                // do nothing.
            }
        };
        PlayResultWrapperRepository playResultWarpperRepository = new PlayResultWrapperRepository(null) {
            @Override
            public PlayResult save(PlayResult playResult) {
                return null;
            }
        };
        RacingCarService racingCarService = new RacingCarService(carsRepository, playResultWarpperRepository);

        while (true) {
            String carNames = RacingInputCarView.getCarNames();
            int targetDistance = RacingInputCarView.getTargetDistance();
            RacingResponse racingResponse = racingCarService.startRacing(carNames, targetDistance);
            RacingResultView.printRacingResponse(racingResponse);
        }
    }

}
