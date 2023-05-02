package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class BaseRepository<T, ID extends Serializable> {

    protected final JdbcTemplate jdbcTemplate;

    public BaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

}
