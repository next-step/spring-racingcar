package racingcar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class ServiceTest extends MockTest{

    private ValidatorFactory factory;
    private Validator validator;

    public ServiceTest() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();    }

    @AfterEach
    void reset() {
        factory.close();
    }

    public <T> List<ParameterValid> validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        return validate.stream().map(v -> new ParameterValid(v.getPropertyPath().toString(), v.getMessage())).collect(Collectors.toList());
    }
    public static class ParameterValid {

        String path;
        String message;

        public String getPath() {
            return path;
        }

        public String getMessage() {
            return message;
        }

        public ParameterValid(String path, String message) {
            this.path = path;
            this.message = message;
        }
    }
}
