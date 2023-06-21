package racingcar.repository;

import racingcar.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<T extends BaseEntity, ID extends Serializable> {
    T save(T entity);

    List<T> findAll();

    void deleteAll();

}
