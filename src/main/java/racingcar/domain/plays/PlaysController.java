package racingcar.domain.plays;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<PlaysDTO.Response>> get() {
        return ResponseEntity.ok(playsService.read());
    }

}
