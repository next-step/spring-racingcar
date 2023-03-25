package racingcar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import racingcar.domain.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {

    Cars save(Cars cars);

    Optional<Cars> findById(Long id);

    List<Cars> findAll();
}
