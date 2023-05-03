package racingcar.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class PlaysRequestDto {
	private static final String NAME_SPLIT_KEYWORD = ",";

	private String names;
	private Integer count;

	public List<String> getNames() {
		return Arrays.asList(this.names.split(NAME_SPLIT_KEYWORD));
	}
}
