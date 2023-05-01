package racingcar.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Records {
	private long id;
	private long trialCount;
	private String winners;
	private String racingCars;
	private Date createdAt;
}
