package racingcar;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import racingcar.console.controller.RacingCarConsoleController;

@Profile("!test")
@RequiredArgsConstructor
@Component
public class ConsoleApplicationRunner implements CommandLineRunner {

    private final RacingCarConsoleController consoleController;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            consoleController.play();
        }
    }
}
