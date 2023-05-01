package racingcar.domain.plays;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/plays")
@RequiredArgsConstructor
public class PlaysController {

    private final PlaysService playsService;

    @PostMapping("")
    public ResponseEntity<PlaysDTO.Response> post(@RequestBody PlaysDTO.Request request) {
        PlaysDTO.Response res = playsService.create(request);
        return ResponseEntity.created(URI.create("/")).body(res);
    }

}
