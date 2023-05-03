package racingcar.api.controller;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<String> jsonExceptionHandle() {
        return ResponseEntity.badRequest().body("데이터를 읽어오던 중 오류가 발생했습니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validExceptionHandle() {
        return ResponseEntity.badRequest().body("입력이 올바르지 않습니다.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeExceptionHandle() {
        return ResponseEntity.badRequest().body("오류가 발생했습니다.");
    }
}

