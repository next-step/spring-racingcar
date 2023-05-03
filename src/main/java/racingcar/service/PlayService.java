package racingcar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import racingcar.dao.RecordDao;
import racingcar.domain.Cars;

@Service
@RequiredArgsConstructor
public class PlayService {
	private final RecordDao recordDao;

	public Cars play(List<String> names, int count) throws JsonProcessingException {
		Cars cars = new Cars(names);
		cars.play(count);
		recordDao.insert(cars, count);
		return cars;
	}
}
