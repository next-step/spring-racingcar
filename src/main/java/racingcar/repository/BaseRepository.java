package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Set;

public abstract class BaseRepository<T, ID extends Serializable> {

    protected final JdbcTemplate jdbcTemplate;
    protected final Validator validator;


    public BaseRepository(JdbcTemplate jdbcTemplate, Validator validator) {
        this.jdbcTemplate = jdbcTemplate;
        this.validator = validator;
    }

    /**
     * 객체가 저장되지 않은 엔티티라고 판단하면 저장한다.
     *
     * @param entity 단일 엔티티
     * @return 저장된 엔티티
     */
    public abstract T save(T entity);

    /**
     * 객체를 저장한다.
     *
     * @param entity 단일 엔티티
     * @return 저장된 엔티티
     */
    protected abstract T insert(T entity);

    /**
     * 객체의 유효성을 검사한다.
     * @param entity 단일 엔티티
     */
    protected void validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
