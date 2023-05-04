package racingcar.dto;

public class RacingCarRequestDto {
	String names;
	int count;

	public RacingCarRequestDto(String names, int count) {
		this.names = names;
		this.count = count;
	}

	public String getNames() {
		return names;
	}

	public int getCount() {
		return count;
	}
}
