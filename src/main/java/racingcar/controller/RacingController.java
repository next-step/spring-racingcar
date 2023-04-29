package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.RacingRequest;
import racingcar.controller.dto.RacingResponse;
import racingcar.service.RacingService;

@RestController
public class RacingController {

    private RacingService racingService;
    private JdbcTemplate jdbcTemplate;

    public RacingController(RacingService racingService, JdbcTemplate jdbcTemplate) {
        this.racingService = racingService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> request(@RequestBody RacingRequest racingRequest) {
        RacingResponse response = racingService.playGame(racingRequest);
        return ResponseEntity.ok().body(response);
    }

}
