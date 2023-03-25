package racingcar;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import racingcar.model.RacingResponse;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private RacingCarService racingCarService;

    public Application (RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        RacingResponse racingResponse = racingCarService.racingGame(
                inputView.getCarNames(),
                inputView.getTryNo());

        resultView.printResult(racingResponse);
    }
}
