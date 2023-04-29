package racingcar.dto;

import lombok.Getter;

@Getter
public class RacingRequestDto {

  private final String names;
  private final int count;

  public RacingRequestDto(String names, int count) {
    this.names = names;
    this.count = count;
  }
}
