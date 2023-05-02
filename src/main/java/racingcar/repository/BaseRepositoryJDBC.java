package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.entity.BaseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public abstract class BaseRepositoryJDBC<T extends BaseEntity, ID extends Serializable> implements BaseRepository<T, ID> {

    protected final JdbcTemplate jdbcTemplate;
    protected final Validator validator;


    public BaseRepositoryJDBC(JdbcTemplate jdbcTemplate, Validator validator) {
        this.jdbcTemplate = jdbcTemplate;
        this.validator = validator;
    }

    /**
     * 객체가 저장되지 않은 엔티티라고 판단하면 저장한다.
     *
     * @param entity 단일 엔티티
     * @return 저장된 엔티티
     */
    public T save(T entity) {
        this.validate(entity);
        if (entity.getId() == null) {
            return insert(entity);
        } else {
            throw new IllegalArgumentException("이미 저장된 데이터입니다.");
        }
    }

    /**
     * 객체를 저장한다.
     * @param entity 단일 엔티티
     * @param insertSql insert 쿼리
     * @param pss sql과 entity 매핑
     * @return 저장된 엔티티
     */
    protected T insert(T entity, String insertSql, PreparedStatementSetter pss) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        entity.setCreatedDate(LocalDateTime.now());

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            pss.setValues(ps);
            return ps;
        }, keyHolder);

        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return entity;
    }

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
