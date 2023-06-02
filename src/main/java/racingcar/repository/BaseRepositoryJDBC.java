package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.entity.BaseEntity;
import racingcar.utils.annotation.GeneratedValue;
import racingcar.utils.annotation.IdField;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class BaseRepositoryJDBC<T extends BaseEntity, ID extends Serializable> implements BaseRepository<T, ID> {

    protected final JdbcTemplate jdbcTemplate;
    protected final Validator validator;


    public BaseRepositoryJDBC(JdbcTemplate jdbcTemplate, Validator validator) {
        this.jdbcTemplate = jdbcTemplate;
        this.validator = validator;
    }

    protected List<T> findAll(String tableName, RowMapper rowMapper) {
        return jdbcTemplate.query("SELECT * FROM " + tableName, rowMapper);
    }


    /**
     * 테이블의 모든 데이터들을 삭제한다.
     *
     * @param tableName 테이블 이름
     */
    protected void deleteAll(String tableName) {
        jdbcTemplate.update("DELETE FROM " + tableName);
    }

    /**
     * 엔티티에 id 어노테이션이 존재한다면 id를 리턴한다.
     *
     * @param entity 단일 엔티티
     * @return 엔티티의 id
     */
    protected ID getId(T entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(IdField.class)) {
                field.setAccessible(true);
                try {
                    return (ID) field.get(entity);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Unable to access ID field", e);
                } catch (ClassCastException e) {
                    throw new RuntimeException("ID field cannot cast", e);
                }
            }
        }
        throw new RuntimeException("No ID field found");
    }

    /**
     * 엔티티에 id 어노테이션이 존재한다면 id를 설정한다.
     *
     * @param entity 단일 엔티티
     * @param id     엔티티의 id
     */
    protected void setId(T entity, ID id) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(IdField.class)) {
                field.setAccessible(true);
                try {
                    field.set(entity, id);
                    return;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Unable to access ID field", e);
                } catch (ClassCastException e) {
                    throw new RuntimeException("ID field cannot cast", e);
                }
            }
        }
        throw new RuntimeException("No ID field found");
    }

    /**
     * 객체가 저장되지 않은 엔티티라고 판단하면 저장한다.
     *
     * @param entity 단일 엔티티
     * @return 저장된 엔티티
     */
    @Override
    public T save(T entity) {
        this.validate(entity);
        if (entity.isNew()) {
            return insert(entity);
        } else {
            throw new IllegalArgumentException("이미 저장된 데이터입니다.");
        }
    }

    /**
     * 객체를 저장한다.
     *
     * @param entity    단일 엔티티
     * @param insertSql insert 쿼리
     * @param pss       sql과 entity 매핑
     * @return 저장된 엔티티
     */
    protected T insert(T entity, String insertSql, PreparedStatementSetter pss) {
        entity.setCreatedDate(LocalDateTime.now());
        if (isGeneratedValueEntity(entity)) {
            return insertGeneratedValue(entity, insertSql, pss);
        } else {
            throw new IllegalArgumentException("현재 지원하지 않는 기능입니다.");
        }
    }


    /**
     * 객체를 저장하고 자동 생성된 키를 등록한다.
     *
     * @param entity    단일 엔티티
     * @param insertSql insert 쿼리
     * @param pss       sql과 entity 매핑
     * @return 저장된 엔티티
     */
    protected T insertGeneratedValue(T entity, String insertSql, PreparedStatementSetter pss) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            pss.setValues(ps);
            return ps;
        }, keyHolder);

        Number id = Objects.requireNonNull(keyHolder.getKey());
        setId(entity, (ID) id);

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
     *
     * @param entity 단일 엔티티
     */
    private void validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private boolean isGeneratedValueEntity(T entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(IdField.class) && field.isAnnotationPresent(GeneratedValue.class)) {
                return true;
            }
        }
        return false;
    }
}
