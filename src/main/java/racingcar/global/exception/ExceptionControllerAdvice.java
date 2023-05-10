package racingcar.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final String MESSAGE = "입력이 올바르지 않습니다.";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validExceptionHandle() {
        return ResponseEntity.badRequest().body(MESSAGE);
    }

}
