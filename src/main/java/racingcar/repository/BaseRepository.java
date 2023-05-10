package racingcar.repository;

import racingcar.entity.BaseEntity;

import java.io.Serializable;

public interface BaseRepository<T extends BaseEntity, ID extends Serializable> {
    T save(T entity);

    void deleteAll();

}
